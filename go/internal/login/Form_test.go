package login_test

import (
	"strings"
	"sync"
	"testing"
	"time"

	rl "github.com/gen2brain/raylib-go/raylib"

	"github.com/SoftDevGang/login-form-tdd-ui-kata/go/internal/login"
)

type testingAuthenticator struct {
	called bool
	user   string
	pass   string

	started   sync.WaitGroup
	completed sync.WaitGroup
	err       error
}

func newTestingAuthenticator() *testingAuthenticator {
	a := &testingAuthenticator{}
	a.started.Add(1)
	a.completed.Add(1)
	return a
}

func (a *testingAuthenticator) Authenticate(user, pass string) error {
	a.called = true
	a.user = user
	a.pass = pass
	a.started.Done()

	a.completed.Wait()
	return a.err
}

// UI simulation.
type testingUI struct {
	buttonCalled  map[string]bool
	buttonText    map[string]string
	buttonBounds  map[string]rl.Rectangle
	buttonResults map[string]bool

	textBoxCalled    map[string]bool
	textBoxText      map[string]string
	textBoxUserInput map[string]string // User interaction could stand out from other fields?
}

func newTestingUI() *testingUI {
	ui := &testingUI{
		buttonCalled:     make(map[string]bool),
		buttonText:       make(map[string]string),
		buttonBounds:     make(map[string]rl.Rectangle),
		buttonResults:    make(map[string]bool),
		textBoxCalled:    make(map[string]bool),
		textBoxText:      make(map[string]string),
		textBoxUserInput: make(map[string]string),
	}
	return ui
}

func (ui *testingUI) TextBox(id string, bounds rl.Rectangle, text string) string {
	ui.textBoxCalled[id] = true
	ui.textBoxText[id] = text
	// If no input is registered by the test, return the passed value. This assumes that the user did not
	// change the value.
	if input, hasInput := ui.textBoxUserInput[id]; hasInput {
		delete(ui.textBoxUserInput, id) // reset value after reporting input
		return input
	}
	return text
}

func (ui *testingUI) Label(id string, bounds rl.Rectangle, text string) {
}

func (ui *testingUI) Button(id string, bounds rl.Rectangle, text string) bool {
	ui.buttonCalled[id] = true
	ui.buttonText[id] = text
	ui.buttonBounds[id] = bounds
	result := ui.buttonResults[id]
	ui.buttonResults[id] = false // reset button click after report
	return result
}

// ***** There is a "Log in" button in right corner of the dialog. *****
// right corner is design.

func TestForm_LoginButton(t *testing.T) {
	var form login.Form
	ui := newTestingUI()

	form.Render(ui)

	if !ui.buttonCalled["login"] {
		t.Errorf("not found")
	}
}

func TestForm_LoginButtonText(t *testing.T) {
	var form login.Form
	ui := newTestingUI()

	form.Render(ui)

	if "Log in" != ui.buttonText["login"] {
		t.Errorf("is not \"Log in\"")
	}
}

func TestForm_LoginButtonBounds(t *testing.T) {
	// now we talk about layout. Options:
	// 1. we could abstract Coordinates -> map coordinates
	// 2. we could move coordinates into the UI's responsibility -> more code in UI
	// 3. we ignore coordinates and check manually -> need to update all calls later
	// 4. we use Raylib coordinates and just mock what is hurting us -> use Raylib in tests
	var form login.Form
	ui := newTestingUI()

	form.Render(ui)

	// first idea: if ui.buttonCoordinate != BottomRight {
	expectedBounds := rl.Rectangle{235, 165, 235 + 110, 165 + 30} // needed to open GIMP
	if ui.buttonBounds["login"] != expectedBounds {
		t.Errorf("expected %v, but was %v", expectedBounds, ui.buttonBounds)
	}
	// Peter:
	// I do not feel good because I believe the coordinates will change a lot while working on it.
	// We assert on layout.
	// If we would use 1 screen shot for the layout in the end (Approvals), then we would not need
	// to assert the button, color, text etc. We would only test that button triggers.
}

// ***** There is a user name field, which is limited to 20 characters. *****

func TestForm_UserNameField(t *testing.T) {
	var form login.Form
	ui := newTestingUI()

	form.Render(ui)

	if !ui.textBoxCalled["username"] {
		t.Errorf("not found")
	}
}

// ignoring design

func TestForm_UserNameFieldInputKept(t *testing.T) {
	var form login.Form
	ui := newTestingUI()
	ui.textBoxUserInput["username"] = "Christian"

	form.Render(ui)

	// we test whether the form keeps input
	if form.UserName != "Christian" {
		t.Errorf("not kept")
	}
	// need more tests because UI lib is IM/stateless?
	// Peter surprised that I need to test for keeping the state.
	// Christian says he is used to having the "UI model" of the IM state.
	// Maybe the missing state makes it easier to separate?
}

func TestForm_UserNameFieldInputDisplayed(t *testing.T) {
	var form login.Form
	form.UserName = "Peter"
	ui := newTestingUI()

	form.Render(ui)

	// we test whether the next render call receives the new text
	if ui.textBoxText["username"] != "Peter" {
		t.Errorf("not displayed")
	}
}

// test for initial text empty skipped, it is empty.

func TestForm_UserNameFieldInputLimited(t *testing.T) {
	original := strings.Repeat("a", login.UserNameLimit)
	var form login.Form
	form.UserName = original
	ui := newTestingUI()
	ui.textBoxUserInput["username"] = form.UserName + "f"

	form.Render(ui)

	if form.UserName != original {
		t.Errorf("not limited \"%v\"", form.UserName)
	}
}

// ***** The label "Phone, email or username" is next to the input field. *****

// Do we need to test the label? It is only style?
// Typical style = colours, positions, pixel perfect stuff.
// Does it matter, we test everything which we can easily?
// Screen shot does not say what is wrong. The message is the image diff. We need to show the diff.
//   Christian likes images. Peter thinks it's heavy weight.

// What about the label?
// Peter would write a unit test.
// Christian would do all presentation stuff with screen shot like a schema validation.
// -> just wrote the code.

// btw. the test UI does not force any design.

// ***** There is a password field, which is limited to 20 characters. *****

func TestForm_PasswordField(t *testing.T) {
	var form login.Form
	ui := newTestingUI()

	form.Render(ui)

	if !ui.textBoxCalled["password"] {
		t.Errorf("not found")
	}
	// now we need an ID of the field because we have 2.
	// * use order of invocations -> can NOT reorder statements on refactor.
	// * add ID to our API (only for tests). IDs are used in other library imgui as well.
}

// skip tests for state, similar to 1st field
// skip tests for limit, similar to 1st field

// ***** The password is either visible as asterisk or bullet signs. *****

func TestForm_PasswordFieldIsDisplayedMasked(t *testing.T) {
	t.SkipNow()

	var form login.Form
	form.Password = "secret"
	ui := newTestingUI()

	form.Render(ui)

	// this does not work, RayGui cannot do this because we need to change the rendering.
	// need to create your own function to use Password function. Will never see "*", only in screen shot.
	if ui.textBoxText["password"] != "*****" {
		t.Errorf("not masked")
	}
	// ImGui can do it with ImGuiInputTextFlags_Password = 1 << 15 ... Password mode, display all characters as '*'
}

// ***** The label "Password" is next to the input field. *****

// skip tests, similar to 1st label

// ***** There is a label in a red box above the button(s). It is only visible if there is an error. *****

// need test to not show error message when empty
// need test to show error message when not empty
// need test error message is red (LabelEx) - maybe not test because styling

// ***** User name and password given, button "Log in" clicked, backend reports success, dialog is closed. *****

func TestForm_CallsAuthenticatorWhenLoginButtonUsed(t *testing.T) {
	username, password := "user1", "secret2"
	var form login.Form
	authenticator := newTestingAuthenticator()
	form.Authentication.Authenticator = authenticator
	ui := newTestingUI()

	form.UserName = username
	form.Password = password
	ui.buttonResults["login"] = true
	form.Render(ui)

	authenticator.started.Wait()
	if !authenticator.called {
		t.Errorf("authenticator not called")
	}
	if authenticator.user != username {
		t.Errorf("wrong username passed")
	}
	if authenticator.pass != password {
		t.Errorf("wrong password passed")
	}
	authenticator.completed.Done()
}

func TestForm_IsBusyWhileAuthenticating(t *testing.T) {
	username, password := "user1", "secret2"
	var form login.Form
	authenticator := newTestingAuthenticator()
	form.Authentication.Authenticator = authenticator
	ui := newTestingUI()

	form.UserName = username
	form.Password = password
	ui.buttonResults["login"] = true
	form.Render(ui)

	if !form.Authentication.InProgress {
		t.Errorf("not busy at start")
	}

	authenticator.started.Wait()
	form.Render(ui)
	if !form.Authentication.InProgress {
		t.Errorf("not busy during authentication")
	}

	authenticator.completed.Done()
	renderWhile(func() bool { return form.Authentication.InProgress }, &form, ui)
	if form.Authentication.InProgress {
		t.Errorf("busy when finished")
	}
}

func renderWhile(condition func() bool, form *login.Form, ui login.FormUI) bool {
	result := false
	endTime := time.Now().Add(time.Second * 3)
	for condition() {
		result = form.Render(ui)
		now := time.Now()
		if now.After(endTime) {
			return result
		}
	}
	return result
}

// skip test login button not allowed when busy, we see how it would look like
//   form.Render(ui) // -> renders form disabled (no button, whatever ...)

func TestForm_LoginNotComplete(t *testing.T) {
	var form login.Form
	ui := newTestingUI()

	result := form.Render(ui)

	if result {
		t.Errorf("is complete")
	}
}

func TestForm_ReturnTrueWhenAuthenticatingSuccess(t *testing.T) {
	username, password := "user1", "secret2"
	var form login.Form
	authenticator := newTestingAuthenticator()
	form.Authentication.Authenticator = authenticator
	ui := newTestingUI()

	form.UserName = username
	form.Password = password
	ui.buttonResults["login"] = true
	form.Render(ui)

	authenticator.started.Wait()
	authenticator.completed.Done()
	result := renderWhile(func() bool { return form.Authentication.InProgress }, &form, ui)
	if !result {
		t.Errorf("not successful")
	}
}

// ***** User name and password given, button "Log in" clicked, backend reports no success, show message in error line. *****

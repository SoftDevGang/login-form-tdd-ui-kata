package login_test

import (
	"testing"

	rl "github.com/gen2brain/raylib-go/raylib"

	"github.com/SoftDevGang/login-form-tdd-ui-kata/go/internal/login"
)

type TestingUI struct {
	buttonCalled bool
	buttonText   string
	buttonBounds rl.Rectangle

	textBoxCalled bool
}

func (ui *TestingUI) TextBox(bounds rl.Rectangle, text string) string {
	ui.textBoxCalled = true
	return text
}

func (ui *TestingUI) Button(bounds rl.Rectangle, text string) bool {
	ui.buttonCalled = true
	ui.buttonText = text
	ui.buttonBounds = bounds
	return false
}

// * There is a "Log in" button in right corner of the dialog.
// right corner is design.

func TestForm_LoginButton(t *testing.T) {
	var form login.Form
	var ui TestingUI
	form.Render(&ui)
	if !ui.buttonCalled {
		t.Errorf("not found")
	}
}

func TestForm_LoginButtonText(t *testing.T) {
	var form login.Form
	var ui TestingUI
	form.Render(&ui)
	if "Log in" != ui.buttonText {
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
	var ui TestingUI
	form.Render(&ui)
	// first idea: if ui.buttonCoordinate != BottomRight {
	expectedBounds := rl.Rectangle{235, 165, 235 + 110, 165 + 30} // needed to open GIMP
	if ui.buttonBounds != expectedBounds {
		t.Errorf("expected %v, but was %v", expectedBounds, ui.buttonBounds)
	}
	// Peter:
	// I do not feel good because I believe the coordinates will change a lot while working on it.
	// We assert on layout.
	// If we would use 1 screen shot for the layout in the end (Approvals), then we would not need
	// to assert the button, color, text etc. We would only test that button triggers.
}

// * There is a user name field, which is limited to 20 characters.

func TestForm_UserNameField(t *testing.T) {
	var form login.Form
	var ui TestingUI
	form.Render(&ui)
	if !ui.textBoxCalled {
		t.Errorf("not found")
	}
}

// * The label "Phone, email or username" is next to the input field.
// * There is a password field, which is limited to 20 characters.
// * The password is either visible as asterisk or bullet signs.
// * The label "Password" is next to the input field.
// * There is a label in a red box above the button(s). It is only visible if there is an error.

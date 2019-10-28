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
}

func (ui *TestingUI) Button(bounds rl.Rectangle, text string) bool {
	ui.buttonCalled = true
	ui.buttonText = text
	ui.buttonBounds = bounds
	return false
}

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

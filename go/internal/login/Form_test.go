package login_test

import (
	"testing"

	rl "github.com/gen2brain/raylib-go/raylib"

	"github.com/SoftDevGang/login-form-tdd-ui-kata/go/internal/login"
)

type MockedUI struct {
	buttonCalled bool
}

func (ui *MockedUI) Button(bounds rl.Rectangle, text string) bool {
	ui.buttonCalled = true
	return false
}

func TestLoginButtonOnForm(t *testing.T) {
	var form login.Form
	var ui MockedUI
	form.Render(&ui)
	if !ui.buttonCalled {
		t.Errorf("Button() was not called")
	}
}

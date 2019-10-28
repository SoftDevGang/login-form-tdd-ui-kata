package login_test

import (
	"testing"

	"github.com/SoftDevGang/login-form-tdd-ui-kata/go/internal/login"
)

type TestingUI struct {
	buttonCalled bool
	buttonLabel  string
}

func (ui *TestingUI) Button(label string) bool {
	ui.buttonCalled = true
	ui.buttonLabel = label
	return false
}

func TestFormHasLoginButton(t *testing.T) {
	var form login.Form
	var ui TestingUI
	form.Render(&ui)
	if !ui.buttonCalled {
		t.Errorf("Button() was not called")
	}
}

func TestForm_LoginButtonLabel(t *testing.T) {
	var form login.Form
	var ui TestingUI
	form.Render(&ui)
	if "Log in" != ui.buttonLabel {
		t.Errorf("is not \"Log in\"")
	}
}

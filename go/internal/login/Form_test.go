package login_test

import (
	"testing"

	"github.com/SoftDevGang/login-form-tdd-ui-kata/go/internal/login"
)

type TestingUI struct {
	buttonCalled bool
}

func (ui *TestingUI) Button() bool {
	ui.buttonCalled = true
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

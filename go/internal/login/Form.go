package login

import (
	"github.com/gen2brain/raylib-go/raygui"
	rl "github.com/gen2brain/raylib-go/raylib"
)

type FormUI interface {
	Button(bounds rl.Rectangle, text string) bool
	TextBox(bounds rl.Rectangle, text string) string
}

type RaylibFormUI struct{}

func (ui *RaylibFormUI) Button(label string) bool {
	return raygui.Button(rl.Rectangle{}, label)
}

const UserNameLimit = 20

// Form renders the controls for providing the necessary login credentials.
type Form struct {
	UserName string // Go style export more than we usually do.
}

func (form *Form) Render(ui FormUI) {
	form.UserName = ui.TextBox(rl.Rectangle{}, form.UserName)
	if len(form.UserName) > UserNameLimit {
		form.UserName = form.UserName[:UserNameLimit]
	}
	ui.Button(rl.Rectangle{235, 165, 345, 195}, "Log in")
}

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

// Form renders the controls for providing the necessary login credentials.
type Form struct {
	UserName string // Go style export more than we usually do.
}

func (form *Form) Render(ui FormUI) {
	form.UserName = ui.TextBox(rl.Rectangle{}, "foo")
	ui.Button(rl.Rectangle{235, 165, 345, 195}, "Log in")
}

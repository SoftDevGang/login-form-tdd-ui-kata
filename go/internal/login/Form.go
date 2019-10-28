package login

import (
	"github.com/gen2brain/raylib-go/raygui"
	rl "github.com/gen2brain/raylib-go/raylib"
)

type FormUI interface {
	Button(label string) bool
}

type RaylibFormUI struct{}

func (ui *RaylibFormUI) Button(label string) bool {
	return raygui.Button(rl.Rectangle{}, label)
}

// Form renders the controls for providing the necessary login credentials.
type Form struct{}

func (form Form) Render(ui FormUI) {
	ui.Button("Log in")
}

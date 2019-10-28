package login

import rl "github.com/gen2brain/raylib-go/raylib"

type FormUI interface {
	Button(bounds rl.Rectangle, text string) bool
}

// Form renders the controls for providing the necessary login credentials.
type Form struct{}

func (form Form) Render(ui FormUI) {
	ui.Button(rl.Rectangle{}, "")
}

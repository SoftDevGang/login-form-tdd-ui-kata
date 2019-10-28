package login

import (
	"github.com/gen2brain/raylib-go/raygui"
	rl "github.com/gen2brain/raylib-go/raylib"
)

type FormUI interface {
	Button(id string, bounds rl.Rectangle, text string) bool
	TextBox(id string, bounds rl.Rectangle, text string) string
	Label(id string, bounds rl.Rectangle, text string)
}

type RaylibFormUI struct{}

func (ui *RaylibFormUI) Button(id string, bounds rl.Rectangle, text string) bool {
	return raygui.Button(rl.Rectangle{}, text)
}

func (ui *RaylibFormUI) TextBox(id string, bounds rl.Rectangle, text string) string {
	return raygui.TextBox(bounds, text)
}

func (ui *RaylibFormUI) Label(id string, bounds rl.Rectangle, text string) {
	raygui.Label(bounds, text)
}

const UserNameLimit = 20

// Form renders the controls for providing the necessary login credentials.
type Form struct {
	UserName string // Go style export more than we usually do.
	Password string
}

func (form *Form) Render(ui FormUI) {
	userNameLabelBounds := rl.Rectangle{}
	ui.Label("username", userNameLabelBounds, "Phone, email or username")

	// TODO move out bounds to map of [id]bounds into UI type.
	userNameBounds := rl.Rectangle{X: 0, Y: 40, Width: 100, Height: 30}
	// Idea is to move out the bounds into the ui, because the id gives a 1:1 lookup
	// on the bounds. We do not assert the bounds in unit test, so we do not need to
	// have them here. We said it's styling and will be asserted using a single screen shot
	// for the whole form.
	// Maybe moving out stuff is against IM nature because the whole UI is to be in code.
	// Are we allowed to delegate styling.

	form.UserName = ui.TextBox("username", userNameBounds, form.UserName)
	if len(form.UserName) > UserNameLimit {
		form.UserName = form.UserName[:UserNameLimit]
	}

	passwordBounds := rl.Rectangle{X: 0, Y: 80, Width: 100, Height: 30}
	form.Password = ui.TextBox("password", passwordBounds, form.Password)

	buttonBounds := rl.Rectangle{X: 235, Y: 165, Width: 345, Height: 195}
	ui.Button("login", buttonBounds, "Log in")
}

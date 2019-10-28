package main

import (
	rl "github.com/gen2brain/raylib-go/raylib"

	"github.com/SoftDevGang/login-form-tdd-ui-kata/go/internal/login"
)

func main() {
	rl.InitWindow(800, 450, "raylib [core] example - basic window")
	rl.SetTargetFPS(20)

	var form login.Form
	var ui login.RaylibFormUI

	for !rl.WindowShouldClose() {
		rl.BeginDrawing()
		rl.ClearBackground(rl.RayWhite)

		form.Render(&ui)

		rl.EndDrawing()
	}

	rl.CloseWindow()
}

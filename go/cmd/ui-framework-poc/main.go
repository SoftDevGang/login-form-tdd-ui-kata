package main

import (
	"fmt"

	"github.com/gen2brain/raylib-go/raylib" // nolint: goimports
)

// main showcases a basic "hello world" example of running a raylib window.
// Use this application to verify compilation and runtime capability of your system.
func main() {
	rl.InitWindow(800, 450, "raylib [core] example - basic window")
	rl.SetTargetFPS(20)

	for !rl.WindowShouldClose() {
		rl.BeginDrawing()
		rl.ClearBackground(rl.RayWhite)

		rl.DrawText(fmt.Sprintf("Congrats! You created your first window!"), 190, 200, 20, rl.LightGray)

		rl.EndDrawing()
	}

	rl.CloseWindow()
}

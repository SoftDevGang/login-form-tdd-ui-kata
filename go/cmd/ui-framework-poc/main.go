package main

import (
	"fmt"

	"github.com/gen2brain/raylib-go/raygui"
	rl "github.com/gen2brain/raylib-go/raylib"
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

		if raygui.Button(rl.Rectangle{
			X:      0,
			Y:      0,
			Width:  100,
			Height: 20,
		}, "something") {
			fmt.Printf("yay!\n")
		}

		rl.EndDrawing()
	}

	rl.CloseWindow()
}

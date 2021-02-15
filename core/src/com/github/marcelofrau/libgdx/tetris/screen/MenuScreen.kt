package com.github.marcelofrau.libgdx.tetris.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils
import com.github.marcelofrau.libgdx.tetris.TetrisGame

class MenuScreen(
    private val game: TetrisGame) : Screen {

    private val camera: OrthographicCamera = OrthographicCamera()

    init {
        camera.setToOrtho(false, 800f, 400f)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f);

        camera.update()

        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        game.font.draw(game.batch, "Welcome to Tetris", 100f, 150f)
        game.font.draw(game.batch, "Tap anywhere to begin", 100f, 100f)
        game.batch.end()

        if (Gdx.input.isTouched) {
            game.screen = GameScreen(game)
            dispose()
        }

    }

    override fun show() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }

}
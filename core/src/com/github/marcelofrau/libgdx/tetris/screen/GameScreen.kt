package com.github.marcelofrau.libgdx.tetris.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.ScreenUtils
import com.github.marcelofrau.libgdx.tetris.TetrisGame

class GameScreen(
    private val game: TetrisGame) : Screen {

    private val camera: OrthographicCamera = OrthographicCamera()
    private val shapeRenderer: ShapeRenderer = ShapeRenderer()

    init {
        camera.setToOrtho(false, 800f, 400f)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f);

        camera.update()

        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        game.batch.end()

        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = Color.RED
        shapeRenderer.rect(0f, 0f, 10f, 10f)
        shapeRenderer.

        shapeRenderer.end()
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
        shapeRenderer.dispose()
    }
}
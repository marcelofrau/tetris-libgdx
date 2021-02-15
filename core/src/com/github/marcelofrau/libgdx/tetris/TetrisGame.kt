package com.github.marcelofrau.libgdx.tetris

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.marcelofrau.libgdx.tetris.screen.MenuScreen


class TetrisGame : Game() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()

        setScreen(MenuScreen(this))
    }

    override fun render() {
        super.render();
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}
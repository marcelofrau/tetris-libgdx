package com.github.marcelofrau.libgdx.tetris.desktop

import kotlin.jvm.JvmStatic
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.github.marcelofrau.libgdx.tetris.TetrisGame

fun main(arg: Array<String>) {
    LwjglApplication(TetrisGame(), getConfig())
}

private fun getConfig(): LwjglApplicationConfiguration {
    return LwjglApplicationConfiguration().apply {
        title = "Test"
        width = 800
        height = 480
    }
}

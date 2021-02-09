package com.github.marcelofrau.libgdx.tetris

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.TimeUtils

import com.badlogic.gdx.math.MathUtils





class TetrisGame : ApplicationAdapter() {

    private lateinit var dropImage: Texture
    private lateinit var bucketImage: Texture
    private lateinit var dropSound: Sound
    private lateinit var rainMusic: Music
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch
    private lateinit var bucket: Rectangle

    private val raindrops: MutableList<Rectangle> = mutableListOf()

    private var lastDropTime: Long = 0

    override fun create() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = Texture(Gdx.files.internal("drop.png"))
        bucketImage = Texture(Gdx.files.internal("bucket.png"))

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"))
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"))

        // start the playback of the background music immediately
        rainMusic.isLooping = true
        rainMusic.play()

        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)

        batch = SpriteBatch()

        bucket = Rectangle()
        bucket.x = (800 / 2 - 64 / 2).toFloat()
        bucket.y = 20f
        bucket.width = 64f
        bucket.height = 64f

        spawnRaindrop()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)

        camera.update()

        batch.projectionMatrix = camera.combined
        batch.begin()
        raindrops.forEach {
            batch.draw(dropImage, it.x, it.y)
        }
        batch.draw(bucketImage, bucket.x, bucket.y)
        batch.end()

        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos[Gdx.input.x.toFloat(), Gdx.input.y.toFloat()] = 0f

            println(touchPos)
            camera.unproject(touchPos)
            bucket.x = touchPos.x - 64 / 2
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 400 * Gdx.graphics.deltaTime
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 400 * Gdx.graphics.deltaTime

        if(bucket.x < 0) bucket.x = 0f;
        if(bucket.x > 800 - 64) bucket.x = 800f - 64f;

        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        val iterator = raindrops.iterator();
        while (iterator.hasNext()) {
            val raindrop = iterator.next();
            raindrop.y -= 200 * Gdx.graphics.deltaTime
            if(raindrop.y + 64 < 0) iterator.remove();
        }


    }

    private fun spawnRaindrop() {
        val raindrop = Rectangle()
        raindrop.x = MathUtils.random(0, 800 - 64).toFloat()
        raindrop.y = 480f
        raindrop.width = 64f
        raindrop.height = 64f

        raindrops.add(raindrop)
        lastDropTime = TimeUtils.nanoTime()
    }

    override fun dispose() {
        dropImage.dispose()
        bucketImage.dispose()
        dropSound.dispose()
        rainMusic.dispose()
        batch.dispose()
    }
}
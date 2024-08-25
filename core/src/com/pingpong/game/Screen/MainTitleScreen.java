package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pingpong.game.GameHandler;
import com.pingpong.game.Input.RectangleInputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class MainTitleScreen implements Screen {

	final GameHandler game;
	private OrthographicCamera camera;
	private Stage stage;
	private GameScreen gameScreen;
	private InputMultiplexer inputMultiplexer;
	private RectangleInputProcessor rectangleInputProcessor;

	public MainTitleScreen(final GameHandler game) {

		this.game = game;
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800, 480);
		this.stage = new Stage(new ScreenViewport());
		this.gameScreen = new GameScreen(game);
		this.rectangleInputProcessor = new RectangleInputProcessor(game, this.gameScreen.getRectangle());
		this.inputMultiplexer = new InputMultiplexer();
		this.inputMultiplexer.addProcessor(this.stage);
		this.inputMultiplexer.addProcessor(this.rectangleInputProcessor);
		Gdx.input.setInputProcessor(this.inputMultiplexer);

	}

	@Override
	public void resize(int width, int height) {

		Gdx.app.log("MainTitleScreen.java", "resize method");
	}

	@Override
	public void pause() {

		Gdx.app.log("MainTitleScreen.java", "pause method");
	}

	@Override
	public void show() {

		Gdx.app.log("MainTitleScreen.java", "show method");
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		TextButton newGame = new TextButton("New Game", skin);
		TextButton multiplayer = new TextButton("Multiplayer", skin);
		TextButton exit = new TextButton("Exit", skin);

		table.add(newGame).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(multiplayer).fillX().uniformX();
		table.row();
		table.add(exit).fillX().uniformX();

		newGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "new game button pressed");
				game.setScreen(gameScreen);
			}

		});
		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "exit button pressed");
				Gdx.app.exit();
			}
		});
	}

	@Override
	public void render(float delta) {
		// clear the screen ready for next set of images to be drawn
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();

	}

	@Override
	public void hide() {

		Gdx.app.log("MainTitleScreen.java", "hide method");
	}

	@Override
	public void dispose() {

		Gdx.app.log("MainTitleScreen.java", "dispose method");
		stage.dispose();
	}

	@Override
	public void resume() {

		Gdx.app.log("MainTitleScreen.java", "resume method");
	}
}

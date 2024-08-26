package com.pingpong.game.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.pingpong.game.GameHandler;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class MainTitleScreen implements Screen {

	final GameHandler game;

	public MainTitleScreen(final GameHandler game) {
		this.game = game;
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
		this.game.getMainTitleStage().addActor(table);
		Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		TextButton newGame = new TextButton("Start/Resume", skin);
		TextButton multiplayer = new TextButton("Multiplayer", skin);
		TextButton connect = new TextButton("Connect", skin);
		TextButton exit = new TextButton("Exit", skin);

		table.add(newGame).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(multiplayer).fillX().uniformX();
		table.row();
		table.add(connect).fillX().uniformX();
		table.row();
		table.add(exit).fillX().uniformX();

		newGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "new game button pressed");
				game.setScreen(game.getGameScreen());
			}

		});

		multiplayer.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "Starting Server Socket");
				ServerSocket server = Gdx.net.newServerSocket(Net.Protocol.TCP, "localhost",
						8080,
						new ServerSocketHints());

				Gdx.app.log("MainTitleScreen.java", "Waiting for connection");
				Socket socket = server.accept(new SocketHints());
				Gdx.app.log("MainTitleScreen.java", "client connected");
				OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream();

				try {
					outputStream.write("sent this to the server".getBytes());
					Gdx.app.log("[Server]: MainTitleScreen", "data sent");
				} catch (IOException e) {
					Gdx.app.log("[Server]: MainTitleScreen", "error sending data", e);
				}

			}

		});

		connect.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "connecting to server");
				Socket socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 8080,
						new SocketHints());
				Gdx.app.log("MainTitleScreen.java", "connected successfully");
				OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream();

				byte[] b = new byte[1024];

				try {
					inputStream.read(b);
					Gdx.app.log("[Client]: MainTitleScreen", "data received");
				} catch (IOException e) {
					Gdx.app.log("[Client]: MainTitleScreen", "error reading data", e);
				}

				String readedData = new String(b);
				Gdx.app.log("[Client]", readedData);
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
		this.game.clearScreen();
		this.game.getMainTitleStage().draw();
	}

	@Override
	public void hide() {

		Gdx.app.log("MainTitleScreen.java", "hide method");
	}

	@Override
	public void dispose() {

		Gdx.app.log("MainTitleScreen.java", "dispose method");
		this.game.getMainTitleStage().dispose();
	}

	@Override
	public void resume() {

		Gdx.app.log("MainTitleScreen.java", "resume method");
	}
}

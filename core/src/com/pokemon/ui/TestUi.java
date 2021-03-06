package com.pokemon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.pokemon.screen.GameScreen;
import com.pokemon.screen.MainMenuScreen;

public class TestUi{
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    MainMenuScreen mainMenuScreen;

    public TestUi() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));


        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setPosition(0,-75);
        Label idLabel = new Label("ID:", skin);
        final TextField idText = new TextField("", skin);
        idText.setMaxLength(12);

        Label passwordLabel = new Label("PASSWORD:", skin);
        final TextField passwordText = new TextField("", skin);
        passwordText.setPasswordMode(true);
        passwordText.setPasswordCharacter('*');
        passwordText.setMaxLength(15);

        TextButton loginButton = new TextButton("LOGIN", skin);
        TextButton signupButton = new TextButton("SIGN UP", skin);

//        table.setDebug(true);

        table.add(idLabel).right();
        table.add(idText).width(150).pad(5);

        table.row();
        table.add(passwordLabel).right();
        table.add(passwordText).width(150).pad(5);

        table.row();
        table.add(loginButton).width(200).height(50).pad(5).colspan(2);

        table.row();
        table.add(signupButton).width(200).height(50).pad(5).colspan(2);

        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("ID: " + idText.getText());
                System.out.println("PASSWORD: " + passwordText.getText());
                gameStart();
            }
        });

    }

    public void gameSet(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
    }

    public void gameStart() {
        mainMenuScreen.gameStart();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void update() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}

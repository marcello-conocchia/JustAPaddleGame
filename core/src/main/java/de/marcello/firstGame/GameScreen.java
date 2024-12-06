package de.marcello.firstGame;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {

    ShapeRenderer shapeRenderer;
    Vector2 paddlePosition;
    Vector2 ballPosition;
    Vector2 ballVelocity;
    float ballRadius = 15.0f;
    float maxVelocity = 550.0f;
    float paddleWidth = 100.00f;
    float paddleHeight = 32.0f;
    Random random;
	public GameScreen() {

        shapeRenderer = new ShapeRenderer();
        paddlePosition = new Vector2(Gdx.graphics.getWidth() / 2 -  paddleWidth / 2, 32.0f);
        ballPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2);
        random = new Random();
        ballVelocity = new Vector2(random.nextFloat() * 200 - 100, 300.0f);
       
	}
	
	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
		paddlePosition.x -= maxVelocity * delta;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
		paddlePosition.x += maxVelocity * delta;
		}

		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if(paddlePosition.x < 0.0) {
			paddlePosition.x = 0.0f;
		}else if (paddlePosition.x > Gdx.graphics.getWidth() - paddleWidth) {
			paddlePosition.x = Gdx.graphics.getWidth() - paddleWidth ;
		}
		
		if(ballPosition.y >= Gdx.graphics.getHeight() - ballRadius) {
			ballPosition.y = Gdx.graphics.getHeight() - ballRadius;
			ballVelocity.y = -ballVelocity.y;
		}
		if(ballPosition.x <= ballRadius) {
			ballPosition.x = ballRadius;
			ballVelocity.x = -ballVelocity.x;
		}
		if(ballPosition.x >= Gdx.graphics.getWidth() ) {
			ballPosition.x = Gdx.graphics.getHeight() - ballRadius;
			ballVelocity.x = -ballVelocity.x;
		}
		if(ballPosition.y <= -ballRadius) {
	        ballPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2);
	        ballVelocity = new Vector2(random.nextFloat() * 200, 300.0f);
		}
		if(ballPosition.y - ballRadius >= paddlePosition.x - (paddleHeight/2) ) {
			if(ballPosition.x >= paddlePosition.x -(paddleWidth /2) && ballPosition.x <= paddlePosition.x + (paddleWidth /2))
		
			ballVelocity.y *= -2.0f;
			
		}
		
		
		
		
		ballPosition.mulAdd(ballVelocity, delta);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.circle(ballPosition.x, ballPosition.y, ballRadius);
        shapeRenderer.rect(paddlePosition.x, paddlePosition.y, paddleWidth, paddleHeight);
        shapeRenderer.end();
        
	}
	
	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
	
	@Override
	public void hide() {
		this.dispose();
	}
}

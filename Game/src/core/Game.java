package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Game implements WindowListener {

	// atributo responsável por fazer que o gameloop continue
	private boolean active;
	// armazena uma referencia criada para a janela do jogo
	private JFrame mainWindow;
	// atributo que armazena o BufferStrategy utilizado para acesso à tela
	// understood == false;
	private BufferStrategy bufferStrategy;

	public Game() {
		// Construtor da Classe

		// Instancia a janela principal
		mainWindow = new JFrame("Desenvolvimento de jogos em java - etapa 1");
		// ajusta as dimensões da janela
		mainWindow.setSize(100, 100);
		// define essa class como ouvinte dos eventos da janela
		mainWindow.addWindowListener(this);
		// desativa o jogo
		active = false;

	}

	public void terminate() {
		// método responsavel por encerrar o jogo atribuindo falso a variavel
		// active

		active = false;

	}

	public void run() {
		// método responsável por fazer o jogo continuar rodando

		active = true;
		// chamamos o metodo antes de iniciar o loop
		load();

		// game loop
		while (active) {
			update();
			render();
		}
		// chamamos esse método apos encerrar o loop encerrando assim a
		// aplicacao
		unload();
	}

	private void load() {
		// Responsavel por carregar os recursos (criação de objetos, carga de
		// imagens e sons, etc)

		// ignora o evento de desenho do sistema
		mainWindow.setIgnoreRepaint(true);

		// Posicionamos a janela a 100 pixels da borda.
		mainWindow.setLocation(100, 100);

		// mostra a janela
		mainWindow.setVisible(true);

		// Criamos a buffer strategy com 2 buffers (double buffer).
		mainWindow.createBufferStrategy(2);

		// Armazena a buffer strategy na nossa variavel para uso posterior.
		bufferStrategy = mainWindow.getBufferStrategy();
		// chamamos o metodo onLoad, que sera implementado pelas
		// classes derivadas.
		onLoad();

	}

	private void unload() {
		// TODO Auto-generated method stub

		// chama o onUnload, que sera implementado
		// pelas classes filhas.
		onUnload();
		// libera a buffer strategy.
		bufferStrategy.dispose();
		// liberamos a janela.
		mainWindow.dispose();

	}

	private void update() {
		// atualiza o jogo a todo momento (verifica se o jogador morreu, se
		// atingiu um
		// inimigo ou se pressionou a tecla para se mover. Chamado a cada volta
		// do game loop).

		// chama o onUpdate que sera implementada pela classe filha
		onUpdate();

		// Em seguida chamamos yeald na nossa Thread para permitir a outras
		// partes do sistema processarem.
		Thread.yield();

	}

	//
	private void render() {
		// A cada chamada a render, obtemos um graphics para desenhar.
		// Ele representa a tela.
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		// Especificamos a cor preta.
		g.setColor(Color.black);
		// desenha um retangulo do tamanho da janela (limpamos a tela).
		g.fillRect(0, 0, mainWindow.getWidth(), mainWindow.getHeight());
		// Chamamos onRender, que sera implementado nas classes derivadas.
		// Nesse metodo sera desenhada a tela do jogo.
		onRender(g);
		// Liberamos o objeto graphics.
		g.dispose();
		// Pedimos ao buffer strategy para mostrar o que foi desenhado acima.
		bufferStrategy.show();

	}

	// Metodo que devera ser implementado nas classes filhas.
	abstract public void onLoad();

	// Metodo que devera ser implementado nas classes filhas.
	abstract public void onUnload();

	// Metodo que devera ser implementado nas classes filhas.
	abstract public void onUpdate();

	// Metodo que devera ser implementado nas classes filhas.
	abstract public void onRender(Graphics2D g);

	public int getWidth() {
		// Retorna a largura da janela.
		return mainWindow.getWidth();
	}

	public int getHeight() {
		// Retorna a altura da janela.
		return mainWindow.getHeight();
	}

	public void windowClosing(WindowEvent e) {
		// Metodo chamado no evento de fechar a janela.
		// Nese momento chamamos terminate para terminar o jogo.
		terminate();
	}

	// Metodo que precisa ser implementado porque implementamos
	// a interface WindowListener, que contem ele.
	public void windowOpened(WindowEvent e) {
	}

	// Metodo que precisa ser implementado porque implementamos
	// a interface WindowListener, que contem ele.
	public void windowClosed(WindowEvent e) {
	}

	// Metodo que precisa ser implementado porque implementamos
	// a interface WindowListener, que contem ele.
	public void windowIconified(WindowEvent e) {
	}

	// Metodo que precisa ser implementado porque implementamos
	// a interface WindowListener, que contem ele.

	public void windowDeiconified(WindowEvent e) {
	}

	// Metodo que precisa ser implementado porque implementamos
	// a interface WindowListener, que contem ele.

	public void windowActivated(WindowEvent e) {
	}

	// Metodo que precisa ser implementado porque implementamos
	// a interface WindowListener, que contem ele.

	public void windowDeactivated(WindowEvent e) {
	}
}

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Sort_by_choice extends Frame implements ActionListener{
	static public ArrayList<Integer> mas;
	static public ArrayList<Integer> left = new ArrayList<Integer>();
	static public ArrayList<Integer> right = new ArrayList<Integer>();
	static public Label arrleft;
	static public Label arrright;
	static public String text = "Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \"Сделано\". Фиолетовый - левая часть, Белый - правая";
	static public int metka=0;
	static public ArrayPanel arrp;
	static public Label l = new Label("");
	static public boolean test_mode;
	public Label warning = new Label("");
	public static String help = "Пока в левой части не все элементы:\n"+
	"в правой части выбрать минимальный элемент и добавить его в конец левой части";
	
	public Sort_by_choice(Boolean test_mode) {
		
		setLayout(null);
		setTitle("Сортировка Выбором");
		setSize(1600,780);
		mas = new ArrayList<Integer>();
    	Random r = new Random();
    	for(int i=0;i<5;i++) {
			mas.add(r.nextInt(10+10)-10);
			right.add(mas.get(i));
		} 
    	if(test_mode==true) {
    		this.test_mode=test_mode;
	    	Button done = new Button("Сделано");
	    	done.addActionListener(this);
	    	done.setBounds(this.getWidth()/2-Strwidth(this,done.getLabel())/2,this.getHeight()-300,Strwidth(this,done.getLabel())+5,20);
	    	add(done);
    	}
    	else {
    		this.test_mode=test_mode;
    		text="Фиолетовые ячейки уже отсортированы и перенесены в левую часть массива";
    		Button next = new Button("Следующий шаг");
	    	next.addActionListener(this);
	    	next.setBounds(this.getWidth()/2-Strwidth(this,next.getLabel())/2,this.getHeight()-300,Strwidth(this,next.getLabel())+5,20);
	    	add(next);
    	}
    	arrp = new ArrayPanel(this,mas,this);
    	arrp.setBounds(this.getWidth()/2-150, 300, 300, 50);
    	add(arrp);
    	arrleft = new Label(left.toString());
    	arrright = new Label(right.toString());
    	arrleft.setBounds(250, 300, 300, 100);
    	arrright.setBounds(this.getWidth()-350, 300, 300, 100);
    	add(arrleft);
    	add(arrright);
    	l.setBounds(this.getWidth()/2-Strwidth(this,l.getText())/2,120,Strwidth(this,l.getText())+1,20);
    	add(l);
		add(warning);
    	Button restart = new Button("Начать заново");
    	restart.addActionListener(this);
    	restart.setBounds(this.getWidth()/2-Strwidth(this,restart.getLabel())/2,this.getHeight()-200,Strwidth(this,restart.getLabel()),20);
    	add(restart);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setVisible(true);
	}

	public static void setmas(Sort_by_choice sbc, ArrayList<Integer> arr,ActionListener al) {
		if(arr!=null) {
			Sort_by_choice.mas=arr;
			sbc.remove(arrp);
			arrp = new ArrayPanel(sbc,mas,sbc);
			arrp.setBounds(sbc.getWidth()/2-150, 300, 300, 50);
			sbc.add(arrp);
			sbc.setSize(sbc.getWidth()-1,sbc.getHeight()-1);
			sbc.setSize(sbc.getWidth()+1,sbc.getHeight()+1);
			sbc.repaint();
		}
	}

	public static int Strwidth(Sort_by_choice sbc, String s) {
		Font font = new Font("Arial", 0, 12);
		FontMetrics fontMetrics = sbc.getFontMetrics(font);
		int w = fontMetrics.stringWidth(s);
		return w;
	}
	
	public void actionPerformed(ActionEvent ae) {
		String s; 
		switch (s = ((Button) ae.getSource()).getLabel()) { 
			case "Сделано": { 
				ArrayList<Integer> test = new ArrayList<Integer>();
				for(int i=0;i<mas.size();i++) {
					test.add(mas.get(i));
				}
				int tmp=0;
				while(tmp!=test.size()) {
		    		chsort_test(test,tmp);
		    		tmp++;
		    	}
				Boolean b=true;
				for(int i=0;i<mas.size();i++) {
					if(mas.get(i)!=test.get(i)) {
						b=false;
						warning.setText("");
						text=("Вы ошиблись в "+(i+1)+"ой ячейке");
						repaint();
						break;
					}					
				}
				if(metka!=mas.size()) {
					if(b==true) {
						warning.setText("Массив отсортирован, но ещё не все элементы в левой части");
						warning.setBounds(this.getWidth()/2-Strwidth(this,warning.getText())/2,200,Strwidth(this,warning.getText())+1,20);
						repaint();
					}			
				}
				else {
					if(b==true) {
						warning.setText("");
						text=("Вы справились, молодец!");
						repaint();
					}
				}
			break; 
			}
			case "Следующий шаг": {
				if(metka!=mas.size()) {
					chsort_test(mas,metka);
					metka++;
					left.add(mas.get(metka-1));
					arrleft.setText(Sort_by_choice.left.toString());
					right.remove(mas.get(metka-1));
					arrright.setText(Sort_by_choice.right.toString());
					remove(arrp);
					arrp = new ArrayPanel(this,mas,this);
					arrp.setBounds(this.getWidth()/2-150, 300, 300, 50);
					add(arrp);
					setSize(getWidth()-1,getHeight()-1);
					setSize(getWidth()+1,getHeight()+1);
					text=Integer.toString(mas.get(metka-1))+" минимальный, переносим его в левую часть массива";
					repaint();
				}
				ArrayList<Integer> test = new ArrayList<Integer>();
				for(int i=0;i<mas.size();i++) {
					test.add(mas.get(i));
				}
				int tmp=metka;
				while(tmp!=test.size()) {
		    		chsort_test(test,tmp);
		    		tmp++;
		    	}
				Boolean b=true;
				for(int i=0;i<mas.size();i++) {
					if(mas.get(i)!=test.get(i)) {
						b=false;
						break;
					}					
				}
				if(metka!=mas.size()) {
					if(b==true) {
						warning.setText("Массив отсортирован, но ещё не все элементы в левой части");
						warning.setBounds(this.getWidth()/2-Strwidth(this,warning.getText())/2,200,Strwidth(this,warning.getText())+1,20);
						repaint();
					}			
				}
				else {
					if(b==true) {
						warning.setText("");
						text=("Массив отсортирован");
						repaint();
					}	
				}
				if(!b) {
					
				}
			break;	
			}
			case "Начать заново": {
				reset(this);
			break;	
			}
		}
	}
	
	public void drawCenteredString(String s, int w, int h, Graphics g) {
		FontMetrics fm = g .getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;		
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent()))/2);
		g.drawString(s, x, y);
	}
	
	public void chsort_test(ArrayList<Integer> arr,int metka) {
		int min=metka;
		for(int i=metka+1;i<arr.size();i++) {
			if(arr.get(i)<arr.get(min)) {
				min=i;
			}
		}
		int tmp=arr.get(min);
		arr.set(min,arr.get(metka));
		arr.set(metka,tmp);
}
	
	public static void reset(Sort_by_choice sbc) {

		Random r = new Random();
		mas=new ArrayList<Integer>();
		left=new ArrayList<Integer>();
		right=new ArrayList<Integer>();
		metka=0;
		for(int i=0;i<5;i++) {
			mas.add(r.nextInt(10+10)-10);
			right.add(mas.get(i));
		}
		sbc.remove(arrp);
		l.setText("");
		arrp = new ArrayPanel(sbc,mas,sbc);
		arrp.setBounds(sbc.getWidth()/2-150, 300, 300, 50);
		sbc.add(arrp);
		sbc.setSize(sbc.getWidth()-1,sbc.getHeight()-1);
		sbc.setSize(sbc.getWidth()+1,sbc.getHeight()+1);
		arrleft.setText(left.toString());
		arrright.setText(right.toString());
    	arrleft.setBounds(250, 300, 300, 100);
    	arrright.setBounds(sbc.getWidth()-350, 300, 300, 100);
    	sbc.warning.setText("");
    	sbc.add(arrleft);
    	sbc.add(arrright);
    	if(test_mode==true) {
    		text="Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \\\"Сделано\\\". Фиолетовый - левая часть, Белый - правая";
    	}
    	else {
    		text="Фиолетовые ячейки уже отсортированы и перенесены в левую часть массива";
    	}
    	sbc.repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawCenteredString("Алгоритм", this.getWidth(), 200, g);
		drawCenteredString(Sort_by_choice.help, this.getWidth(), 250, g);
		if(text!=null) {
			drawCenteredString(text, this.getWidth(), 100, g);
		}
		if(test_mode==false) {
			text="Фиолетовые ячейки уже отсортированы и перенесены в левую часть массива";
		}
		else {
			text="Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \\\"Сделано\\\". Фиолетовый - левая часть, Белый - правая";
		}
	}
}

class TestWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent we) {
	System.exit(0);
	}
}
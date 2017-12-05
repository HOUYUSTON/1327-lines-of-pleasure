import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Sort_by_choice extends Frame implements ActionListener{//without static и перенести в конструктор
	static public ArrayList<Integer> mas;
	static public ArrayList<Integer> left = new ArrayList<Integer>();
	static public ArrayList<Integer> right = new ArrayList<Integer>();
	static public Label arrleft;
	static public Label arrright;
	static public String text = "Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \"Сделано\". Фиолетовый - левая часть, Белый - правая";
	static public int metka=0;
	static public ArrayPanel arrp;
	static public Label l = new Label("");//все из мэйна в конструктор без t. в мэйне только создать объект класса
	static public boolean test_mode;
	public Label warning = new Label("");
	
	public Sort_by_choice(Boolean test_mode) {
		
		addMouseListener(new TestMouseAdapter(this));
		//addWindowListener(new TestWindowAdapter());
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
	    	done.setBounds(this.getWidth()/2-Strwidth(this,done.getLabel())/2,this.getHeight()-200,Strwidth(this,done.getLabel()),20);
	    	add(done);
    	}
    	else {
    		this.test_mode=test_mode;
    		text="Фиолетовые ячейки уже отсортированы и перенесены в левую часть массива";
    		Button next = new Button("Следующий шаг");
	    	next.addActionListener(this);
	    	next.setBounds(this.getWidth()/2-Strwidth(this,next.getLabel())/2,this.getHeight()-200,Strwidth(this,next.getLabel()),20);
	    	add(next);
    	}
    	arrp = new ArrayPanel(this,mas,this);
    	arrp.setBounds(this.getWidth()/2-150, 150, 300, 100);
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
    	restart.setBounds(this.getWidth()/2-Strwidth(this,restart.getLabel())/2,this.getHeight()-100,Strwidth(this,restart.getLabel()),20);
    	add(restart);
    	setLocationRelativeTo(null);
    	setExtendedState(Frame.MAXIMIZED_BOTH);
    	setVisible(true);
	}
	
	public static void main(String args[]) {
		//Sort_by_choice sbt = new Sort_by_choice();
	}

	public static void setmas(Sort_by_choice sbc, ArrayList<Integer> arr,ActionListener al) {
		if(arr!=null) {
			Sort_by_choice.mas=arr;
			sbc.remove(arrp);
			arrp = new ArrayPanel(sbc,mas,sbc);
			arrp.setBounds(sbc.getWidth()/2-150, 150, 300, 100);
			sbc.add(arrp);
			sbc.repaint();
		}
	}

	public static int Strwidth(Sort_by_choice sbc, String s) {
		Font font = new Font("Arial", 1, 12);
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
				int tmp=metka;
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
						warning.setBounds(this.getWidth()/2-Strwidth(this,warning.getText())/2,100,Strwidth(this,warning.getText())+1,20);
						//text=("Массив отсортирован, но ещё не все элементы в левой части");
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
					arrp.setBounds(this.getWidth()/2-150, 150, 300, 100);
					add(arrp);
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
						warning.setBounds(this.getWidth()/2-Strwidth(this,warning.getText())/2,100,Strwidth(this,warning.getText())+1,20);
						//text=("Массив отсортирован, но ещё не все элементы в левой части");
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
				/*Random r = new Random();
				mas=new ArrayList<Integer>();
				left=new ArrayList<Integer>();
				right=new ArrayList<Integer>();
				metka=0;
				for(int i=0;i<5;i++) {
					mas.add(r.nextInt(10+10)-10);
					right.add(mas.get(i));
				}
				remove(arrp);
				l.setText("");
				arrp = new ArrayPanel(this,mas,this);
				arrp.setBounds(this.getWidth()/2-150, 150, 300, 100);
				add(arrp);
				arrleft.setText(left.toString());
				arrright.setText(right.toString());
		    	arrleft.setBounds(250, 300, 300, 100);
		    	arrright.setBounds(this.getWidth()-350, 300, 300, 100);
		    	add(arrleft);
		    	add(arrright);
		    	if(test_mode==true) {
		    		text="Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \\\"Сделано\\\". Фиолетовый - левая часть, Белый - правая";
		    	}
		    	else {
		    		text="Фиолетовые ячейки уже отсортированы и перенесены в левую часть массива";
		    	}
		    	repaint();*/
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
		arrp.setBounds(sbc.getWidth()/2-150, 150, 300, 100);
		sbc.add(arrp);
		arrleft.setText(left.toString());
		arrright.setText(right.toString());
    	arrleft.setBounds(250, 300, 300, 100);
    	arrright.setBounds(sbc.getWidth()-350, 300, 300, 100);
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
		//super.paint(g);
		if(text!=null) {
			drawCenteredString(text, this.getWidth(), 100, g);
		}		
	}
}

class ArrayPanel extends Panel { 
	
	ArrayPanel(Sort_by_choice sbc,ArrayList<Integer> arr, ActionListener al){
		for(int i=0;i<arr.size();i++) {
			Button b = new Button(Integer.toString(arr.get(i)));
			if(Sort_by_choice.left.size()>=i+1) {
				if(arr.get(i)==Sort_by_choice.left.get(i)) {
					b.setBackground(Color.magenta);
				}
			}
			b.addActionListener(al);
			if(Sort_by_choice.test_mode==true) {
			b.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			    	if (e.getButton() == MouseEvent.BUTTON1){
			    		
			    		if(find(Sort_by_choice.right,Integer.parseInt(b.getLabel()))!=-1337) {
			    			Sort_by_choice.left.add(Integer.parseInt(b.getLabel()));
				    		Sort_by_choice.arrleft.setText(Sort_by_choice.left.toString());
				    		Sort_by_choice.right.remove(find(Sort_by_choice.right,Integer.parseInt(b.getLabel())));
				    		Sort_by_choice.arrright.setText(Sort_by_choice.right.toString());				    		
				    		ArrayList<Integer> arr = new ArrayList<Integer>();
				    		for(int i=0;i<Sort_by_choice.left.size();i++) {
				    			arr.add(Sort_by_choice.left.get(i));
				    		}
				    		for(int i=Sort_by_choice.left.size();i<Sort_by_choice.right.size()+Sort_by_choice.left.size();i++) {
				    			arr.add(Sort_by_choice.right.get(i-Sort_by_choice.left.size()));
				    		}
				    		Sort_by_choice.setmas(sbc,arr,al);
				    		Sort_by_choice.metka++;
			    		}
			          //если нажата правая кнопка--тут выполнить действие ее нажатия
			          //P.S. BUTTON1--Левая кнопка, BUTTON2--колесо мыши                      
			         }
			      }
			  });
			}
			else {
				b.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(MouseEvent e) {
				    	if (e.getButton() == MouseEvent.BUTTON1){		
				    		
				    		if(find(Sort_by_choice.right,Integer.parseInt(b.getLabel()))!=-1337) {
				    			Sort_by_choice.l.setText("элемент находиться в правой части");
				    			Sort_by_choice.l.setBounds(sbc.getWidth()/2-Sort_by_choice.Strwidth(sbc,Sort_by_choice.l.getText())/2,120,Sort_by_choice.Strwidth(sbc,Sort_by_choice.l.getText())+1,20);
				    		}     
				    		else {
				    			Sort_by_choice.l.setText("элемент находиться в левой части");
				    			Sort_by_choice.l.setBounds(sbc.getWidth()/2-Sort_by_choice.Strwidth(sbc,Sort_by_choice.l.getText())/2,120,Sort_by_choice.Strwidth(sbc,Sort_by_choice.l.getText())+1,20);
				    		}
				         }
				      }
				  });
			}
			add(b);			
		}
	}
	
	public int find(ArrayList<Integer> arr,int a){
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i)==a) {
				return i;
			}
		}
		return -1337;
	}
	
	public void paint(Graphics g) { 
			super.paint(g); 
	} 
}

class TestMouseAdapter extends MouseAdapter {
	Sort_by_choice t;
	public TestMouseAdapter(Sort_by_choice t) {
		this.t = t;
}
	public void mousePressed(MouseEvent me) {
		t.repaint();
	}
}

class TestWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent we) {
	System.exit(0);
	}
}
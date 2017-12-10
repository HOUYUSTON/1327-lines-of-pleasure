import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Sort_by_bubble extends Frame implements ActionListener{
	static public ArrayList<Integer> mas;
	static public ArrayList<Integer> left = new ArrayList<Integer>();
	static public ArrayList<Integer> right = new ArrayList<Integer>();
	static public Label arrleft;
	static public Label arrright;
	static public String text = "Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \"Сделано\". Фиолетовый - левая часть, Белый - правая, Бирюзовый  - пузырёк";
	static public int metka=0;
	static public ArrayPanel arrp;
	static public Label l = new Label("");
	static public boolean test_mode;
	static public int bubble=-1337;
	public Label warning = new Label("");
	public static String help = "Пока в левой части не все элементы:\n"+
	"в правой части взять последний элемент(пузырёк) и сравнить с предыдущим, если пузырёк меньше, то поменть их местами, если нет, то назначить предидущий пузырьком, пока не дойдёте до левого края правой части";//< - меньше
	
	public Sort_by_bubble(Boolean test_mode) {
		
		setLayout(null);
		setTitle("Сортировка Пузырьком");
		setSize(1600,780);
		mas = new ArrayList<Integer>();
    	Random r = new Random();
    	int c=0;
    	while (mas.size()<5) {
    	    int i = r.nextInt(10+10)-10;
    	    if (!mas.contains(i)) {
    	        mas.add(i);
    	        right.add(mas.get(c));
    	        c++;
    	    }
    	}
    	if(test_mode==true) {
    		this.test_mode=test_mode;
	    	Button done = new Button("Сделано");
	    	done.addActionListener(this);
	    	done.setBounds(this.getWidth()/2-Strwidth(this,done.getLabel())/2,this.getHeight()-300,Strwidth(this,done.getLabel())+5,20);
	    	add(done);
	    	Button replace = new Button("Переместить разделитель");
	    	replace.addActionListener(this);
	    	replace.setBounds(this.getWidth()/2-Strwidth(this,replace.getLabel())/2,this.getHeight()-350,Strwidth(this,replace.getLabel())+5,20);
	    	add(replace);
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
    	warning.setBounds(this.getWidth()/2-Strwidth(this,warning.getText())/2,200,Strwidth(this,warning.getText())+1,20);
		add(warning);
    	Button restart = new Button("Начать заново");
    	restart.addActionListener(this);
    	restart.setBounds(this.getWidth()/2-Strwidth(this,restart.getLabel())/2,this.getHeight()-200,Strwidth(this,restart.getLabel()),20);
    	add(restart);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setVisible(true);
	}

	public static void setmas(Sort_by_bubble sbb, ArrayList<Integer> arr,ActionListener al) {
		if(arr!=null) {			
			Sort_by_bubble.mas=arr;
			sbb.remove(arrp);
			arrp = new ArrayPanel(sbb,mas,sbb);
			arrp.setBounds(sbb.getWidth()/2-150, 300, 300, 50);
			sbb.add(arrp);
			sbb.setSize(sbb.getWidth()-1,sbb.getHeight()-1);
			sbb.setSize(sbb.getWidth()+1,sbb.getHeight()+1);
			sbb.repaint();
		}
	}

	public static int Strwidth(Sort_by_bubble sbb, String s) {
		Font font = new Font("Arial", 0, 12);
		FontMetrics fontMetrics = sbb.getFontMetrics(font);
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
						warning.setBounds(getWidth()/2-Strwidth(this,warning.getText())/2,200,Strwidth(this,warning.getText())+1,20);
						warning.setText("Массив отсортирован, но ещё не все элементы в левой части");
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
				ArrayList<Integer> test = new ArrayList<Integer>();
				for(int i=0;i<mas.size();i++) {
					test.add(mas.get(i));
				}
				int c=0;
				while(c!=test.size()) {
		    		chsort_test(test,c);
		    		c++;
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

					if(ArrayPanel.find(right,bubble)==0) {
						bubble=mas.get(mas.size()-1);
						left.add(right.get(0));
						arrleft.setText(left.toString());	
						right.remove(0);
		    			arrright.setText(right.toString());
		    			metka++;
		    			remove(arrp);
		    			arrp = new ArrayPanel(this,mas,this);
		    			arrp.setBounds(getWidth()/2-150, 300, 300, 50);
		    			add(arrp);
		    			text="Мы дошли до левого края правой части, сдвигаем разделитель";
		    			setSize(getWidth()-1,getHeight()-1);
		    			setSize(getWidth()+1,getHeight()+1);
		    			repaint();
		    			break;
					}
					if(b==true) {
						if(metka==mas.size()-1) {
							bubble=-1337;		
							left.add(right.get(0));
							arrleft.setText(left.toString());	
							right.remove(0);
			    			arrright.setText(right.toString());
			    			metka++;
			    			remove(arrp);
			    			arrp = new ArrayPanel(this,mas,this);
			    			arrp.setBounds(getWidth()/2-150, 300, 300, 50);
			    			add(arrp);
			    			text="Массив отсортирован";
			    			setSize(getWidth()-1,getHeight()-1);
			    			setSize(getWidth()+1,getHeight()+1);
			    			repaint();
						}
					}
					if(bubble==-1337) {
						bubble=mas.get(mas.size()-1);
					}
					if(ArrayPanel.find(right,bubble)>=1) {
						if(right.get(ArrayPanel.find(right,bubble))<right.get(ArrayPanel.find(right,bubble)-1)) {
							int tmp=right.get(ArrayPanel.find(right,bubble)-1);
							int pos=ArrayPanel.find(right,bubble);
							right.set(pos-1,bubble);
							right.set(pos,tmp);
							arrright.setText(right.toString());
							mas.clear();
							for(int i=0;i<left.size();i++){
			    				mas.add(left.get(i));
			    			}
			    			for(int i=0;i<right.size();i++){
			    				mas.add(right.get(i));
			    			}
			    			text="пузырёк меньше чем "+Integer.toString(tmp)+", меняем их местами";
			    			setmas(this,mas,this);
						}
						else {
							bubble=right.get(ArrayPanel.find(right,bubble)-1);
							text=Integer.toString(bubble)+" меньше чем пузырёк, делаем его пузырьком";
							setmas(this,mas,this);
						}
					}
				}
			break;	
			}
			case "Переместить разделитель": {
				if(metka!=mas.size()) {
					left.add(right.get(0));
					arrleft.setText(left.toString());	
					right.remove(0);
	    			arrright.setText(right.toString());
	    			metka++;
	    			remove(arrp);
	    			arrp = new ArrayPanel(this,mas,this);
	    			arrp.setBounds(getWidth()/2-150, 300, 300, 50);
	    			add(arrp);
	    			setSize(getWidth()-1,getHeight()-1);
	    			setSize(getWidth()+1,getHeight()+1);
	    			repaint();
				}
			break;	
			}
			case "Начать заново": {
				reset(this);
			break;	
			}
		}
	}
	
	public int findmin(ArrayList<Integer> arr) {
		int min=0;					
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i)<arr.get(min)) {
				min=i;
			}
		}
		return arr.get(min);
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
	
	public static void reset(Sort_by_bubble sbb) {

		Random r = new Random();
		mas=new ArrayList<Integer>();
		left=new ArrayList<Integer>();
		right=new ArrayList<Integer>();
		bubble=-1337;
		metka=0;
		int c=0;
		while (mas.size()<5) {
    	    int i = r.nextInt(10+10)-10;
    	    if (!mas.contains(i)) {
    	        mas.add(i);
    	        right.add(mas.get(c));
    	        c++;
    	    }
    	}
		sbb.remove(arrp);
		l.setText("");
		arrp = new ArrayPanel(sbb,mas,sbb);
		arrp.setBounds(sbb.getWidth()/2-150, 300, 300, 50);
		sbb.add(arrp);
		sbb.setSize(sbb.getWidth()-1,sbb.getHeight()-1);
		sbb.setSize(sbb.getWidth()+1,sbb.getHeight()+1);
		arrleft.setText(left.toString());
		arrright.setText(right.toString());
    	arrleft.setBounds(250, 300, 300, 100);
    	arrright.setBounds(sbb.getWidth()-350, 300, 300, 100);
    	sbb.warning.setBounds(sbb.getWidth()/2-Strwidth(sbb,sbb.warning.getText())/2,200,Strwidth(sbb,sbb.warning.getText())+1,20);
    	sbb.warning.setText("");
    	sbb.add(arrleft);
    	sbb.add(arrright);
    	if(test_mode==true) {
    		text="Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \\\"Сделано\\\". Фиолетовый - левая часть, Белый - правая";
    	}
    	else {
    		text="Фиолетовые ячейки уже отсортированы и перенесены в левую часть массива";
    	}
    	sbb.repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawCenteredString("Алгоритм", this.getWidth(), 200, g);
		drawCenteredString(Sort_by_bubble.help, this.getWidth(), 250, g);
		if(text!=null) {
			drawCenteredString(text, this.getWidth(), 100, g);
		}
	}
}
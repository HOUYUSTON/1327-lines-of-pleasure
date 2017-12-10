import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Sort_by_insert extends Frame implements ActionListener{
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
	static public int ctrl_v=-1337;
	public Label vst = new Label("Вставляемый: ");
	public Label warning = new Label("");
	public static String help = "Пока в левой части не все элементы:\n"+
	"удалить самый левый в правой части, найти в левой части элемент, который <= вставляемому и записать вставляемый после него";//< - меньше
	
	public Sort_by_insert(Boolean test_mode) {
		
		setLayout(null);
		setTitle("Сортировка Вставкой");
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
		vst.setBounds(this.getWidth()/2-Strwidth(this,vst.getText())/2,350,Strwidth(this,vst.getText())+1,20);
		add(vst);
    	Button restart = new Button("Начать заново");
    	restart.addActionListener(this);
    	restart.setBounds(this.getWidth()/2-Strwidth(this,restart.getLabel())/2,this.getHeight()-200,Strwidth(this,restart.getLabel()),20);
    	add(restart);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setVisible(true);
	}

	public static void setmas(Sort_by_insert sbi, ArrayList<Integer> arr,ActionListener al) {
		if(arr!=null) {
			if(ctrl_v!=-1337) {
				sbi.vst.setText(sbi.vst.getText()+Integer.toString(ctrl_v));
				sbi.vst.setBounds(sbi.getWidth()/2-Strwidth(sbi,sbi.vst.getText())/2,350,Strwidth(sbi,sbi.vst.getText())+1,20);
			}
			Sort_by_insert.mas=arr;
			sbi.remove(arrp);
			arrp = new ArrayPanel(sbi,mas,sbi);
			arrp.setBounds(sbi.getWidth()/2-150, 300, 300, 50);
			sbi.add(arrp);
			sbi.setSize(sbi.getWidth()-1,sbi.getHeight()-1);
			sbi.setSize(sbi.getWidth()+1,sbi.getHeight()+1);
			sbi.repaint();
		}
	}

	public static int Strwidth(Sort_by_insert sbi, String s) {
		Font font = new Font("Arial", 0, 12);
		FontMetrics fontMetrics = sbi.getFontMetrics(font);
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
						if(metka==mas.size()-1) {
							left.add(mas.get(mas.size()-1));
							arrleft.setText(Sort_by_insert.left.toString());
							right.clear();
							arrright.setText(Sort_by_insert.right.toString());
							warning.setText("");
							text=("Вы справились, молодец!");
							remove(arrp);
							arrp = new ArrayPanel(this,mas,this);
							arrp.setBounds(this.getWidth()/2-150, 300, 300, 50);
							add(arrp);
							metka++;
							setSize(getWidth()-1,getHeight()-1);
							setSize(getWidth()+1,getHeight()+1);							
							repaint();
						}
						else {
							warning.setText("Массив отсортирован, но ещё не все элементы в левой части");
							warning.setBounds(this.getWidth()/2-Strwidth(this,warning.getText())/2,200,Strwidth(this,warning.getText())+1,20);
							repaint();
						}
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
					if(ctrl_v==-1337) {
						ctrl_v=findmin(right);
		    			right.remove(ArrayPanel.find(right, ctrl_v));
		    			arrright.setText(right.toString());				    		
			    		ArrayList<Integer> arr = new ArrayList<Integer>();
			    		for(int i=0;i<Sort_by_insert.left.size();i++) {
			    			arr.add(Sort_by_insert.left.get(i));
			    		}
			    		for(int i=Sort_by_insert.left.size();i<Sort_by_insert.right.size()+Sort_by_insert.left.size();i++) {
			    			arr.add(Sort_by_insert.right.get(i-Sort_by_insert.left.size()));
			    		}
			    		text=Integer.toString(ctrl_v)+" минимальный, удаляем его";
			    		setmas(this,arr,this);
					}
					else {
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
						ArrayList<Integer> arr = new ArrayList<Integer>();
		    			for(int i=0;i<mas.size();i++) {
		    				if(i==ArrayPanel.find(mas,right.get(0))) {
		    					arr.add(ctrl_v);
		    				}			    				
		    					arr.add(mas.get(i));			    				
		    			}
		    			left.add(arr.get(left.size()));			    			
			    		arrleft.setText(left.toString());					    		
			    		right.clear();
			    		for(int i=0;i<arr.size();i++) {
			    			if(i>=left.size()) {
			    				right.add(arr.get(i));
			    			}
			    		}
			    		arrright.setText(right.toString());
			    		vst.setText("Вставляемый: ");
			    		text="Вставляем "+Integer.toString(ctrl_v)+" в конец левого массива";
			    		ctrl_v=-1337;
			    		metka++;
			    		setmas(this,arr,this);
			    		if(metka!=mas.size()) {
							if(b==true) {
								if(metka==mas.size()-1) {
									left.add(mas.get(mas.size()-1));
									arrleft.setText(Sort_by_insert.left.toString());
									right.clear();
									arrright.setText(Sort_by_insert.right.toString());
									warning.setText("");
									text=("Массив отсортирован");
									remove(arrp);
									arrp = new ArrayPanel(this,mas,this);
									arrp.setBounds(this.getWidth()/2-150, 300, 300, 50);
									add(arrp);
									setSize(getWidth()-1,getHeight()-1);
									setSize(getWidth()+1,getHeight()+1);							
									repaint();
								}
							}
			    		}
					}
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
	
	public static void reset(Sort_by_insert sbi) {

		Random r = new Random();
		mas=new ArrayList<Integer>();
		left=new ArrayList<Integer>();
		right=new ArrayList<Integer>();
		metka=0;
		ctrl_v=-1337;
		int c=0;
		while (mas.size()<5) {
    	    int i = r.nextInt(10+10)-10;
    	    if (!mas.contains(i)) {
    	        mas.add(i);
    	        right.add(mas.get(c));
    	        c++;
    	    }
    	}
		sbi.remove(arrp);
		l.setText("");
		arrp = new ArrayPanel(sbi,mas,sbi);
		arrp.setBounds(sbi.getWidth()/2-150, 300, 300, 50);
		sbi.add(arrp);
		sbi.setSize(sbi.getWidth()-1,sbi.getHeight()-1);
		sbi.setSize(sbi.getWidth()+1,sbi.getHeight()+1);
		arrleft.setText(left.toString());
		arrright.setText(right.toString());
    	arrleft.setBounds(250, 300, 300, 100);
    	arrright.setBounds(sbi.getWidth()-350, 300, 300, 100);
    	sbi.warning.setText("");
    	sbi.add(arrleft);
    	sbi.add(arrright);
    	sbi.vst.setText("Вставляемый: ");
		sbi.vst.setBounds(sbi.getWidth()/2-Strwidth(sbi,sbi.vst.getText())/2,350,Strwidth(sbi,sbi.vst.getText())+1,20);
		sbi.add(sbi.vst);
    	if(test_mode==true) {
    		text="Выбирайте минимальный элемент и жмите ЛКМ на каждой итерации, если массив уже отсортирован, то можете пропустить след итерации и нажать \\\"Сделано\\\". Фиолетовый - левая часть, Белый - правая";
    	}
    	else {
    		text="Фиолетовые ячейки уже отсортированы и перенесены в левую часть массива";
    	}
    	sbi.repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawCenteredString("Алгоритм", this.getWidth(), 200, g);
		drawCenteredString(Sort_by_insert.help, this.getWidth(), 250, g);
		if(text!=null) {
			drawCenteredString(text, this.getWidth(), 100, g);
		}
	}
}
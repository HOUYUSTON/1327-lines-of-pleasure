import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class Graphouny extends Frame {
	public static Graphouny gr = new Graphouny();
	//static MyButtonPanel mbt;
	
	public Graphouny() {
		addKeyListener(new MyKeyAdapter(this));
		addMouseListener(new MyMouseAdapter(this));
		addWindowListener(new MyWindowAdapter());
	}
	
	public static void main(String args[]) {
		gr.setLayout(null);
		gr.setSize(1600,800);
		gr.setBackground(Color.MAGENTA);
		CheckboxGroup cbg=new CheckboxGroup(); 
		Checkbox cb1=new Checkbox ("Test", cbg, false); 
		Checkbox cb2=new Checkbox ("Study", cbg, false);
		MyButtonPanel mbt1 = new MyButtonPanel(1);
		MyButtonPanel mbt2 = new MyButtonPanel(2);
		mbt1.setBounds(gr.getWidth()/2-400, 100, 800, 50);
		mbt2.setBounds(gr.getWidth()/2-400, 100, 800, 50);
		gr.add(mbt1);
		gr.add(mbt2);
		mbt1.setVisible(false);
		mbt2.setVisible(false);
		cb1.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent ie)
		    {
				Checkbox cb=(Checkbox) ie.getSource();
					if (cb.getState ())
			        {
						mbt2.setVisible(false);
						mbt1.setVisible(true);
						/*if(mbt==null) {
							mbt = new MyButtonPanel(1);
							mbt.setBounds(gr.getWidth()/2-400, 100, 800, 50);
							gr.add(mbt);
						}
						else {
							gr.remove(mbt);//использовать сэт визибл
							mbt.setVisible(false);
							mbt = new MyButtonPanel(1);
							mbt.setBounds(gr.getWidth()/2-400, 100, 800, 50);
							gr.add(mbt);
						}*/
			        }
		    }
		});
		cb2.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent ie)
		    {
				mbt1.setVisible(false);
				mbt2.setVisible(true);
				/*Checkbox cb=(Checkbox) ie.getSource();
				if(mbt==null) {
					mbt = new MyButtonPanel(2);
					mbt.setBounds(gr.getWidth()/2-400, 100, 800, 50);
					gr.add(mbt);
				}
				else {
					gr.remove(mbt);
					mbt.setVisible(false);
					mbt = new MyButtonPanel(2);
					mbt.setBounds(gr.getWidth()/2-400, 100, 800, 50);
					gr.add(mbt);
				}*/
		    }
		});
		gr.add(cb1);
		gr.add(cb2);
		cb1.setBounds(gr.getWidth()/3, 50, 100, 20);
		cb2.setBounds((gr.getWidth()/3)*2, 50, 100, 20);
		gr.setLocationRelativeTo(null);
    	gr.setExtendedState(Frame.MAXIMIZED_BOTH);
		gr.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}

}

class MyKeyAdapter extends KeyAdapter {
	Graphouny gr;
	
	public MyKeyAdapter(Graphouny gr) {
		this.gr = gr;
	}
}

class MyMouseAdapter extends MouseAdapter {
	Graphouny gr;
	public MyMouseAdapter(Graphouny gr) {
		this.gr = gr;
}
	public void mousePressed(MouseEvent me) {
		gr.repaint();
	}
}

class MyWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent we) {
	System.exit(0);
	}
}

class MyButtonPanel extends Panel { 
	
	public MyButtonPanel(int c) {
		setLayout(new FlowLayout());
		Button b1 = new Button("Сортировка выбором");
		setSize(800,50);
		b1.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				if(c==1) {
					Sort_by_choice t = new Sort_by_choice(true);
			    	t.addWindowListener(new WindowAdapter() {
			            public void windowClosing(WindowEvent we) {
			                t.setVisible(false);
			             }
			    	});
			    	t.setLocationRelativeTo(null);
			    	t.setExtendedState(Frame.MAXIMIZED_BOTH);
			    	t.setVisible(true);
			    }
			    if(c==2) {
			    	Sort_by_choice t = new Sort_by_choice(false);
			    	t.addWindowListener(new WindowAdapter() {
			            public void windowClosing(WindowEvent we) {
			                t.setVisible(false);
			             }
			    	});
			    	t.setLocationRelativeTo(null);
			    	t.setExtendedState(Frame.MAXIMIZED_BOTH);
			    	t.setVisible(true);
			    }
			}
		});
		Button b2 = new Button("Сортировка вставками");
		b2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				if(c==1) {
					Sort_by_choice t = new Sort_by_choice(true);
			    	t.addWindowListener(new WindowAdapter() {
			            public void windowClosing(WindowEvent we) {
			                t.setVisible(false);
			             }
			    	});
			    	t.setLocationRelativeTo(null);
			    	t.setExtendedState(Frame.MAXIMIZED_BOTH);
			    	t.setVisible(true);
			    }
			    if(c==2) {
			    	Sort_by_choice t = new Sort_by_choice(false);
			    	t.addWindowListener(new WindowAdapter() {
			            public void windowClosing(WindowEvent we) {
			                t.setVisible(false);
			             }
			    	});
			    	t.setLocationRelativeTo(null);
			    	t.setExtendedState(Frame.MAXIMIZED_BOTH);
			    	t.setVisible(true);
			    }
			}
		});
		Button b3 = new Button("Сортировка пузырьком");
		b3.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				if(c==1) {
					Sort_by_choice t = new Sort_by_choice(true);
			    	t.addWindowListener(new WindowAdapter() {
			            public void windowClosing(WindowEvent we) {
			                t.setVisible(false);
			             }
			    	});
			    	t.setLocationRelativeTo(null);
			    	t.setExtendedState(Frame.MAXIMIZED_BOTH);
			    	t.setVisible(true);
			    }
			    if(c==2) {
			    	Sort_by_choice t = new Sort_by_choice(false);
			    	t.addWindowListener(new WindowAdapter() {
			            public void windowClosing(WindowEvent we) {
			                t.setVisible(false);
			             }
			    	});
			    	t.setLocationRelativeTo(null);
			    	t.setExtendedState(Frame.MAXIMIZED_BOTH);
			    	t.setVisible(true);
			    }
			}
		});
	    Button b4 = new Button("Быстрая сортировка");	
	    b4.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	    if(c==1) {
	    	setBackground(Color.blue);
	    }
	    if(c==2) {
	    	setBackground(Color.yellow);
	    }
	    b1.setBounds(0, this.getHeight()/2-10, this.getWidth()/4, 20);
	    b2.setBounds(this.getWidth()/4, this.getHeight()/2-10, this.getWidth()/4, 20);
	    b3.setBounds(this.getWidth()/2, this.getHeight()/2-10, this.getWidth()/4, 20);
	    b4.setBounds(this.getWidth()/4*3, this.getHeight()/2-10, this.getWidth()/4, 20);
		add(b1); 
		add(b2); 
		add(b3); 
		add(b4);
		}
	
	public void paint(Graphics g) { 
			super.paint(g); 
	} 
}
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ArrayPanel extends Panel { 
	
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
	
	ArrayPanel(Sort_by_insert sort_by_insert,ArrayList<Integer> arr, ActionListener al){
		for(int i=0;i<arr.size();i++) {
			Button b = new Button(Integer.toString(arr.get(i)));
			if(Sort_by_insert.left.size()>=i+1) {
				if(arr.get(i)==Sort_by_insert.left.get(i)) {
					b.setBackground(Color.magenta);
				}
			}
			b.addActionListener(al);
			if(Sort_by_insert.test_mode==true) {
			b.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			    	if (e.getButton() == MouseEvent.BUTTON1){
			    		
			    		if(find(Sort_by_insert.right,Integer.parseInt(b.getLabel()))!=-1337) {
			    			Sort_by_insert.left.add(Integer.parseInt(b.getLabel()));
			    			Sort_by_insert.arrleft.setText(Sort_by_insert.left.toString());
			    			Sort_by_insert.right.remove(find(Sort_by_insert.right,Integer.parseInt(b.getLabel())));
			    			Sort_by_insert.arrright.setText(Sort_by_insert.right.toString());				    		
				    		ArrayList<Integer> arr = new ArrayList<Integer>();
				    		for(int i=0;i<Sort_by_insert.left.size();i++) {
				    			arr.add(Sort_by_insert.left.get(i));
				    		}
				    		for(int i=Sort_by_insert.left.size();i<Sort_by_insert.right.size()+Sort_by_insert.left.size();i++) {
				    			arr.add(Sort_by_insert.right.get(i-Sort_by_insert.left.size()));
				    		}
				    		Sort_by_insert.setmas(sort_by_insert,arr,al);
				    		Sort_by_insert.metka++;
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
				    		
				    		if(find(Sort_by_insert.right,Integer.parseInt(b.getLabel()))!=-1337) {
				    			Sort_by_insert.l.setText("элемент находиться в правой части");
				    			Sort_by_insert.l.setBounds(sort_by_insert.getWidth()/2-Sort_by_insert.Strwidth(sort_by_insert,Sort_by_insert.l.getText())/2,120,Sort_by_insert.Strwidth(sort_by_insert,Sort_by_insert.l.getText())+1,20);
				    		}     
				    		else {
				    			Sort_by_insert.l.setText("элемент находиться в левой части");
				    			Sort_by_insert.l.setBounds(sort_by_insert.getWidth()/2-Sort_by_insert.Strwidth(sort_by_insert,Sort_by_insert.l.getText())/2,120,Sort_by_insert.Strwidth(sort_by_insert,Sort_by_insert.l.getText())+1,20);
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

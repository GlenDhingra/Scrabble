import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

class Scrabble implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3,p2_1,p2_2;
	JButton grid[];
	JButton chk,give_up,end,search;
	JTextField tf1;
	JToggleButton choose[];
	String s1;
	Random rnd = new Random();
	int flag=0,flag1=1,change_p=0,flag3=0,score_1,score_2;
	String temp="";
	JLabel l1,l2;
	Font font;

	Scrabble()
	{
		f1=new JFrame();
		f1.setSize(1000,1000);
		f1.setVisible(true);
		f1.setLayout(null);

		p1=new JPanel();
		p1.setBounds(0,0,800,600);
		p1.setBackground(Color.CYAN);
		p1.setLayout(new GridLayout(8,10,3,3));
		f1.add(p1);
		
		font=new Font("Verdana",Font.BOLD,20); 
		grid=new JButton[80];
		for(int i=0;i<80;i++)
		{
			grid[i]=new JButton();
			grid[i].setBackground(Color.LIGHT_GRAY);
			grid[i].setForeground(Color.WHITE);
			grid[i].setFont(font);
			p1.add(grid[i]);
			grid[i].addActionListener(this);
		}

		p2=new JPanel();
		p2.setBounds(800,0,200,600);
		p2.setLayout(new GridLayout(2,1));
		p2.setBackground(Color.BLUE);
		f1.add(p2);

		p2_1=new JPanel();
		p2_1.setLayout(new GridLayout(4,3,3,3));
			
		p2_2=new JPanel();
		p2.add(p2_1);
		p2.add(p2_2);
	
		p2_1.setBackground(Color.RED);
		p2_2.setBackground(Color.DARK_GRAY);
		
		end=new JButton("End Game");
		end.addActionListener(this);

		chk=new JButton("Check");
		chk.addActionListener(this);

		tf1=new JTextField(20);
		tf1.setEnabled(false);
		tf1.setBackground(Color.GRAY);

		give_up=new JButton("Give-Up");
		give_up.addActionListener(this);
		
		choose=new JToggleButton[12];
		for(int i=0;i<12;i++)
		{
			char c = (char) (rnd.nextInt(26)+'A'); 
			String c1=Character.toString(c);
			choose[i]=new JToggleButton(c1);
			choose[i].addActionListener(this);
			choose[i].setBackground(Color.YELLOW);
			choose[i].setForeground(Color.RED);
			choose[i].setFont(font);
			p2_1.add(choose[i]);
		}
		
		search=new JButton("Search");
		search.addActionListener(this);
		
		p2_2.add(tf1);
		p2_2.add(chk);
		p2_2.add(search);
		p2_2.add(give_up);
		p2_2.add(end);
		
		p3=new JPanel();
		p3.setBounds(0,600,1000,200);
		p3.setBackground(Color.DARK_GRAY);
		p3.setLayout(new GridLayout());
		f1.add(p3);
		l1=new JLabel("Player 1 : "+score_1);
		ImageIcon icon1=new ImageIcon("download.png");
		ImageIcon icon2=new ImageIcon("download.png");
		l1.setIcon(icon1);
		l2=new JLabel("Player 2 : "+score_2);
		l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		l2.setIcon(icon2);
		p3.add(l1);
		p3.add(l2);
		
	}
	void search()
	{
		try
		{
			FileReader fr1=new FileReader("C:\\Users\\Trena\\Desktop\\Scrabble\\Words.txt");
			BufferedReader br1=new BufferedReader(fr1);
			String line="";
			while((line=br1.readLine())!=null)
			{
				if((tf1.getText()).equals(line)==true)
				{
					flag3=1;
					break;
				}
			}
			if(flag3==1)
			{
				JOptionPane.showMessageDialog(f1,"Found");
				points();
				change();
				flag3=0;
			}
			else
			{
				JOptionPane.showMessageDialog(f1,"Not Found","Error Message",JOptionPane.ERROR_MESSAGE);
			}
			for(int i=0;i<12;i++)
			{
				choose[i].setEnabled(true);
			}
			tf1.setEnabled(false);
			tf1.setBackground(Color.LIGHT_GRAY);
			tf1.setText("");
			flag=0;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	void change()
	{
		for(int i=0;i<12;i++)
		{
			char c = (char) (rnd.nextInt(26)+'A'); 
			String c1=Character.toString(c);
			choose[i].setText(c1);
		}
	}
	void points()
	{
		if(change_p==0)
		{
			score_1+=100;
			change_p=1;
			l1.setText("Player 1 : "+score_1);

		}
		else if(change_p==1)
		{
			score_2+=100;
			change_p=0;
			l2.setText("Player 2 : "+score_2);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		for(int i=0;i<12;i++)
		{
			if(e.getSource()==choose[i])
			{
				s1=choose[i].getText();
			}
		}
		for(int j=0;j<12;j++)
		{
			if(choose[j].isSelected()==true)
			{
				for(int i=0;i<80;i++)
				{
					if(e.getSource()==grid[i] && (grid[i].getText()).equals(""))
					{
						grid[i].setText(s1);
						choose[j].setEnabled(false);
						choose[j].setSelected(false);
					}
				}
			}
		}
		if(e.getSource()==chk)
		{
			tf1.setBackground(Color.WHITE);
			flag=1;
		}
		if(flag==1)
		{
			for(int i=0;i<80;i++)
			{
				if(e.getSource() == grid[i])
				{
					String temp=tf1.getText()+grid[i].getText();	
					tf1.setText(temp.toLowerCase());
					break;
				}
			}
	
		}
		if(e.getSource()==give_up)
		{
			change();
			if(change_p==0)
			{
				change_p=1;
			}
			else if(change_p==1)
			{
				change_p=0;
			}
		}
		if(e.getSource()==search)
		{
			if((tf1.getText()).equals(""))
			{
				JOptionPane.showMessageDialog(f1,"Invalid input","Error Message",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				search();
			}
		}
		if(e.getSource()==end)
		{
			if(score_1>score_2)
			{
				JOptionPane.showMessageDialog(f1,"Player 1 WINS");
			}
			else if(score_2 > score_1)
			{
				JOptionPane.showMessageDialog(f1,"Player 2 WINS");
			}
			else
			{
				JOptionPane.showMessageDialog(f1,"Match ends as DRAW");
			}
			f1.dispose();
		}
	}
	public static void main(String args[])
	{
		new Scrabble();
	}
}
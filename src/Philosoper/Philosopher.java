/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Philosoper;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;



/**
 *
 * @author Ankit
 */
public  class Philosopher extends Thread {

     public  final static int THINKING=0;
    public final static int HUNGRY=1;
    public final static int EATING=2;
    public static MainFram mf=new MainFram();

    private final int i;
    private int state;
    private final Semaphore s;
    
    
    
    final static int n = 5;

    final static Philosopher[] philosophers = new Philosopher[n];

    final static Philosoper.Semaphore mutex = new Philosoper.Semaphore(1,1);
    final static String[] st=new String[3];
    
    public  static int[] pilStatus=new int[n];

    Philosopher(int id) {
      this.i = id;
      s = new Semaphore(1);
      state = THINKING;
    }
    
    private Philosopher left() {
      return philosophers[(i+n-1)%n];
    }

    private Philosopher right() {
      return philosophers[(i + 1) % n];
    }
    
    public void run() {
      try {
        while (true) {
          statusShow();
          
          if(state==THINKING)
          {
            thinking();
            mutex.down();
            state = HUNGRY; 
            
          }
          
          else if(state==HUNGRY)
          {
            test(this);
            mutex.up();
            s.acquire();
            state = EATING;
            
          }
          else 
          {
            eating();
            mutex.down();
            state = THINKING;
            
            test(left());  
            test(right());
            mutex.up();
             
          }
          
        }
      } catch(InterruptedException e) {}
    }
    public static void main(String[] args)
    {
        st[0]="Thinking";
        st[1]="Hungry";
        st[2]="Eating";
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        philosophers[0] = new Philosopher(0);
        for (int i = 1; i < n; i++) {
          philosophers[i] = new Philosopher(i);
          
        }
        mf.show();
        
        for(int i=0;i<n;i++)
            pilStatus[i]=0;
        new Thread(philosophers[0],"Philosoper 0").start();
        new Thread(philosophers[1],"Philosoper 1").start();
        new Thread(philosophers[2],"Philosoper 2").start();
        new Thread(philosophers[3],"Philosoper 3").start();
        new Thread(philosophers[4],"Philosoper 4").start();
        
    }

    void test(Philosopher p) {
      if (p.left().state != EATING && p.state == HUNGRY &&
          p.right().state != EATING) {
          try {
              p.state = EATING;
              
              p.s.release();
          } catch (Exception ex) {
              Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }

    void eating() {
      try {
        
        Thread.sleep((long) Math.round(Math.random() * 5000));
        
      } catch (InterruptedException e) {}
    }
    void thinking() {
      try {
         
        Thread.sleep((long) Math.round(Math.random() * 5000));
       
      } catch (InterruptedException e) {}
    }

    void statusShow() {
      String status;
      if(state==THINKING)
      {
          status="Thinking";
          pilStatus[i]=THINKING;
          mf.setPosition(i,THINKING);
          
          
          
      }
      else if(state==EATING)
      {
          status="Eating";
          pilStatus[i]=EATING;
          mf.setPosition(i,EATING);
          
      }
      else 
      {
          status="Hungry";
          pilStatus[i]=HUNGRY;
          mf.setPosition(i,HUNGRY);
      }
        
      System.out.println("Philosopher " + i + " is " + status);
       // mf.setPosition(i);
    }
  }
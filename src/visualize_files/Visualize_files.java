/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualize_files;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author iblow
 */
public class Visualize_files extends Application 
{
    GraphicsContext gc;
    double canvas_w = 500;
    double canvas_h = 500;
    double x1 = canvas_w-400, x2 = canvas_w-400, y1 = canvas_h-400, y2 = canvas_h-400;
    int i = 0;
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("Choose file");
        
        Button clear_btn = new Button();
        clear_btn.setText("Clear plate");
        
        Button chg_depth_btn = new Button();
        chg_depth_btn.setText("Set depth");
        
        TextField chg_depth_tf = new TextField();
        
        
        
        
        chg_depth_btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                //if(chg_depth_tf.getText())
                {
                    
                    int p = Integer.parseInt(chg_depth_tf.getText());
                        
                    System.out.println(chg_depth_tf.getText() + " ");
                    
                    DrawSmth.change_depth(p);
                }
  
                
            }
        });
        
        clear_btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                gc.clearRect(0,0, canvas_w,canvas_h);
                
                
                x1 = x2 = canvas_w-400;
                y1 = y2 = canvas_h-400;
                
                
                
                System.out.println("In handle of clear button");
            
            }
        });
        
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                
                
                Interpreter interpreter = new Interpreter ();
                
                if (interpreter.isAlive())
                {
                    interpreter.interrupt();
                }   
                else
                {
                    interpreter.start();
                }
               
                
                
            }
        });
        
        
        FlowPane root = new FlowPane(10,10);
        root.setAlignment(Pos.CENTER);
        //root.setBackground(Background.EMPTY);
        Scene scene = new Scene(root, 500, 600);
        
        primaryStage.setTitle("Visualize your file");
        primaryStage.setScene(scene);
        
        
        
        Canvas vizual_canvas = new Canvas (canvas_w, canvas_h);
        
        gc = vizual_canvas.getGraphicsContext2D();
        
        
       
        
        
        vizual_canvas.setOnScroll(new EventHandler() 
        {       
            @Override
            public void handle(Event event) 
            {
                
                
                DrawSmth drow_obj = new DrawSmth();
                
                double[] crd1 = drow_obj.draw(gc, x1, y1, 0, 99);
                double[] crd2 = drow_obj.draw(gc, crd1[0], crd1[1], 2*Math.PI/3, 99);
                double[] crd3 = drow_obj.draw(gc, crd2[0], crd2[1], 4*Math.PI/3, 99);
                //double[] crd4 = drow_obj.draw(gc, crd3[0], crd3[1], 6*Math.PI/6, 99);
                //double[] crd5 = drow_obj.draw(gc, crd4[0], crd4[1], 8*Math.PI/6, 99);
                //double[] crd6 = drow_obj.draw(gc, crd5[0], crd5[1], 10*Math.PI/6, 99);
                //здесь будет вызов интерпретатора.....
                /*for ( i = 0; i < 1000; i++) 
                {
                    if(i%2 == 0)
                    {
                        drow_obj.draw(gc, x1, y1, x2+0.02, y2+0.01);
                        x1 = x1+0.02;
                        y1 = y1+0.05;
                        x2 = x2+0.07 * x1;
                        y2 = y2+0.010 * x2 * Math.sin(x1);
                        
                        System.out.println(x1 + " " + y1);
                    }
                    else
                    {
                        drow_obj.draw(gc, x1, y1, x2+0.01, y2+0.02);
                        x1 = -x1+0.05;
                        y1 = -y1+0.02 * Math.sin(x2);
                        x2 = -x2+0.010 * x2;
                        y2 = -y2+0.07 * x2;
                        
                        System.out.println(x1 + " " + y1);
                        System.out.println(x2 + " " + y2);
                    }
                    
                    
                    
                }*/
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        
        //gc.strokeLine(0, 0, 500, 400);
        

        
        root.getChildren().addAll(vizual_canvas, btn, clear_btn, chg_depth_btn,chg_depth_tf);
        

        
        

        primaryStage.show();
 
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author heniroger
 */
public class Gamma {
    
    public Gamma(){
    }
    public ArrayList<Object[]> convertToArrangedArrayList(ArrayList<Object[]> objects){

         for (int i = 0; i < objects.size(); i++) {
            Integer integera = (int)objects.get(i)[0];
            Integer integero = (int) objects.get(i)[1];
            Double db = (double) objects.get(i)[2];
             System.out.print(""+integera+","+integero+","+db+"\t|| \t");
            
        }
         
         ArrayList<Alpha> iterationGroupByDuplication = new ArrayList<>();
         ArrayList<Object[]> iterationArranged = new ArrayList<>();
         int w = 0;
         Alpha alpha =new Alpha();
         boolean already =false;
         while (iterationArranged.size() != objects.size() ) {
                ++w;
                for (int i = 0; i < objects.size(); i++) {
                           //Arrangement des iterations
                           System.out.println("i="+i);

                            if ((int)objects.get(i)[0] == w) {
                                   for (int j = 0; j < iterationGroupByDuplication.size(); j++) {
                                       if (w == iterationGroupByDuplication.get(j).getIteration()) {
                                           iterationArranged.add(objects.get(i));
                                            iterationGroupByDuplication.get(j).getIntArrayList().add((int)objects.get(i)[1]);
                                            iterationGroupByDuplication.get(j).getVijMap().put((int)objects.get(i)[1],(Double)objects.get(i)[2]);
                                            Collections.sort(iterationGroupByDuplication.get(j).getIntArrayList());
                                            System.out.println("==========W Existe"+w);
                                            already =true;
                                            break;
                                        }else{
                                           already =false;
                                       }
                                   }
                                   
                                   if (already==false) {
                                    
                                        System.out.println("w="+w);
                                         iterationArranged.add(objects.get(i));
                                         alpha.getIntArrayList().add((int)objects.get(i)[1]);
                                         alpha.getVijMap().put((int)objects.get(i)[1],(Double)objects.get(i)[2]);
                                       if (alpha.getIteration()==0) {
                                         alpha.setIteration((int)objects.get(i)[0]);
                                         iterationGroupByDuplication.add(alpha);
                                        }
                                }
                                   
 
                                    
                                
                                    
                                
                                     
                            }else{
                               Collections.sort(alpha.getIntArrayList());
                                alpha = new Alpha();
                            }
                }
         }
        
         ArrayList<Object[]> objectsBis = new ArrayList<>();
         for (int i = 0; i < iterationGroupByDuplication.size(); i++) {
            System.out.println(""+iterationGroupByDuplication.get(i).getIteration());
             for (int j = 0; j < iterationGroupByDuplication.get(i).getIntArrayList().size(); j++) {
                 Integer f = iterationGroupByDuplication.get(i).getIntArrayList().get(j);
                  System.out.println("...."+f);
                  objectsBis.add(new Object[]{iterationGroupByDuplication.get(i).getIteration(),
                      iterationGroupByDuplication.get(i).getIntArrayList().get(j),
                      iterationGroupByDuplication.get(i).getVijMap().get(iterationGroupByDuplication.get(i).getIntArrayList().get(j))});
             }
             
            
        }
                  
        for (int i = 0; i < objectsBis.size(); i++) {
            Integer integera = (int)objectsBis.get(i)[0];
            Integer integero = (int) objectsBis.get(i)[1];
            Double db = (double) objectsBis.get(i)[2];
             System.out.print(""+integera+","+integero+","+db+"\t|| \t");
            
        }
        return objectsBis;
        
    }
}

# scala-assignment-06-ashish-tomer

### Implementing Future API

## Count the files available in a directory passed to the method with **Future**

Directory structure example

                Folder 1
                |      |
             ----       ----
             |              |
         File 1         Folder 2
         File 2             |
                            -----
                                |
                            Folder 3
                            |       |
                        File 3      Folder 4
                        File 4          |
                        File 5      ---------
                                    |       |
                                File 6      File 7
                                
                                
<p>
Output : <br/>
F1<br/>
F2<br/>
Fol 2/ Fol 3/ F3<br/>
Fol 2/ Fol 3/ F4<br/>
Fol 2/ Fol 3/ F5<br/>
Fol 2/ Fol 3/ Fol4/ F6<br/>
Fol 2/ Fol 3/ Fol 4/ F7<br/>
<p>
It should be in the **Future**                   

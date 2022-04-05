import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class LeggiFile {
    final static Logger logger = LogManager.getLogger(LeggiFile.class);

    static void main(String[] args) {
        /* try  {
             OutputStream output = new FileOutputStream("src/main/resources/config.properties")
             Properties prop = new Properties();

             // set the properties value
             prop.setProperty("fileInput.path", "localhost");

             // save properties to project root folder
             prop.store(output, null);

             System.out.println(prop);

         } catch (IOException io) {
             io.printStackTrace();
         } */
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties")

            prop.load(input);

            System.out.println("File Input");
            System.out.println(prop.getProperty("fileInput.path"));
            System.out.println("File Output");
            System.out.println(prop.getProperty("fileOutput.path"));
            System.out.println("Posizione Articolo");
            System.out.println(prop.getProperty("fileInput.posizione_descrizione"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger.info('An info log message.')

        File masterLine = new File(prop.getProperty("fileInput.path"))
        File csvOut = new File(prop.getProperty("fileOutput.path"))
        String testata = new File(prop.getProperty("fileOutput.testata"))
        Integer posizione = prop.getProperty("fileInput.posizione_descrizione")?.toInteger()
        csvOut.write(testata +"\n")

        Map masterList = [:]
        def capoTrattato

        masterLine.eachLine { line ->
            def parts = line.split(";")
            def capoAttuale
            def tmpMap = [:]
            if (parts.length != 0) {
                capoAttuale = parts[posizione]
                System.out.println(" CapoAttuale " + capoAttuale);
                if (capoAttuale.equals(capoTrattato)){
                    csvOut <<  """;${parts[0]};${parts[1]};${parts[8]};${parts[9]};${parts[10]};${parts[11]};${parts[12]};;;;;;\n"""

                } else{
                    capoTrattato = parts[posizione]
                    if(!capoTrattato.equals("Descrizione Articolo")){
                        //                        csvOut <<  """$capoTrattato;${parts[1]};;;;;;;${parts[14]};${parts[15]};${parts[15]};${parts[17]};${parts[18]};\n"""
                        csvOut <<  """$capoTrattato;${parts[1]};;;;;;;;;;;;\n"""
                        csvOut <<  """;${parts[0]};${parts[1]};${parts[8]};${parts[9]};${parts[10]};${parts[11]};${parts[12]};;;;;;\n"""

                    }

                }

            }
        }
    }
}
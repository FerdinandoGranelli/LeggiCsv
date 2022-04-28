import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class LeggiFile {
    final static Logger logger = LogManager.getLogger(LeggiFile.class);

    static void main(String[] args) {

        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties")

            prop.load(input);

            System.out.println("File Input");
            System.out.println(prop.getProperty("fileInputGiacenze.path"));
            System.out.println("File Output");
            System.out.println(prop.getProperty("fileOutputGiacenze.path"));
            System.out.println("Posizione Articolo");
            System.out.println(prop.getProperty("fileInput.posizione_descrizione"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger.info('An info log message.')

        File masterLine = new File(prop.getProperty("fileInputGiacenze.path"))
        File csvOut = new File(prop.getProperty("fileOutputGiacenze.path"))
        String testata = new File(prop.getProperty("fileOutputGiacenze.testata"))
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
                    //         SKU        ;PSKU       ;QUANTITà  ;PREZZOEU   ;COLORE      ;TAGLIA      ;QUANTITA    ;QUANTITA
                csvOut <<  """;${parts[0]};${parts[1]};${parts[8]};${parts[9]};${parts[10]};${parts[11]};${parts[12]};;;;;;\n"""

            }
        }
    }
}

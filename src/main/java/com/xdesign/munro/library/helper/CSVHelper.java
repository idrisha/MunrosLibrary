package com.xdesign.munro.library.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xdesign.munro.library.models.Munro;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper
{
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat( MultipartFile file )
    {

        if ( !TYPE.equals( file.getContentType() ) )
        {
            return false;
        }

        return true;
    }

    public static List<Munro> csvToMunros( InputStream is )
    {
        try ( BufferedReader fileReader = new BufferedReader(
                new InputStreamReader( is, "UTF-8" ) ); CSVParser csvParser = new CSVParser( fileReader,
                CSVFormat.DEFAULT.withHeader() ); )
        {

            List<Munro> munros = new ArrayList<Munro>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for ( CSVRecord csvRecord : csvRecords )
            {
                if ( csvRecord.get( "id" ).isEmpty() )
                {
                    continue;
                }
                Munro munro = new Munro( Long.parseLong( csvRecord.get( "id" ) ), csvRecord.get( "DoBIHNumber" ),
                        csvRecord.get( "Streetmap" ), csvRecord.get( "Geograph" ), csvRecord.get( "HillBagging" ),
                        csvRecord.get( "Name" ), csvRecord.get( "SMCSection" ), csvRecord.get( "RHBSection" ),
                        csvRecord.get( "Section" ), csvRecord.get( "HeightInMtrs" ), csvRecord.get( "HeightInFt" ),
                        csvRecord.get( "Map150" ), csvRecord.get( "Map125" ), csvRecord.get( "GridRef" ),
                        csvRecord.get( "GridRefXY" ), csvRecord.get( "xcoord" ), csvRecord.get( "ycoord" ),
                        csvRecord.get( "y1891" ), csvRecord.get( "y1921" ), csvRecord.get( "y1933" ),
                        csvRecord.get( "y1953" ), csvRecord.get( "y1969" ), csvRecord.get( "y1974" ),
                        csvRecord.get( "y1981" ), csvRecord.get( "y1984" ), csvRecord.get( "y1990" ),
                        csvRecord.get( "y1997" ), csvRecord.get( "Post1997" ), csvRecord.get( "Comments" ) );
                munros.add( munro );
            }
            return munros;
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            throw new RuntimeException( "fail to parse CSV file: " + e.getMessage() );
        }
    }

    public static ByteArrayInputStream munrosToCSV( List<Munro> munros )
    {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode( QuoteMode.MINIMAL );

        try ( ByteArrayOutputStream out = new ByteArrayOutputStream(); CSVPrinter csvPrinter = new CSVPrinter(
                new PrintWriter( out ), format ); )
        {
            for ( Munro munro : munros )
            {
                List<String> data = Arrays.asList( String.valueOf( munro.getId() ), munro.getName(),
                        munro.getComments() );

                csvPrinter.printRecord( data );
            }

            csvPrinter.flush();
            return new ByteArrayInputStream( out.toByteArray() );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( "fail to import data to CSV file: " + e.getMessage() );
        }
    }
}

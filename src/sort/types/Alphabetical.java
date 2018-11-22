package sort.types;

import java.io.*;
import java.util.ArrayList;

public class Alphabetical {
    private String inputDirectory, inputFileName;
    private String outputDirectory, outputFileName;
    private String[] bands = {"Metalica", "AC-DC", "Guns and Roses", "Led Zeppelin", "Sixteen tones", "Road to Heaven"};
    private ArrayList<Integer> passedValues = new ArrayList<>();

    private FileWriter InputFileWriter;
    private BufferedWriter iWriter;
    private FileReader InputFileReader;
    private BufferedReader iReader;

    private FileWriter OutputFileWriter;
    private BufferedWriter oWriter;
    private FileReader OutputFileReader;
    private BufferedReader oReader;

    public static void main(String[] args) {
        Alphabetical alphabetical = new Alphabetical();
        alphabetical.run();
    }

    public void run(){
        for(int counter = 0; counter < bands.length; counter++){
            passedValues.add(counter);
        }
        createFileWithInput("Data", "Input.txt");
        createFileWithOutput("Data", "Output.txt");
        sortDataInFile("Data", "Input.txt");
    }

    private void createFileWithOutput(String outputDirectory, String outputFileName) {
        this.outputDirectory = outputDirectory;
        this.outputFileName = outputFileName;
    }

    public void createFileWithInput(String inputDirectory, String inputFileName){
        this.inputDirectory = inputDirectory;
        this.inputFileName = inputFileName;
        int outerCounter = 0;
        int random;
        try{
            File directory = new File(this.inputDirectory);
            directory.mkdir();
            InputFileWriter = new FileWriter(this.inputDirectory + "/" + this.inputFileName);
            iWriter = new BufferedWriter(InputFileWriter);
            do{
                random = (int)(Math.random() * (passedValues.size() - 1));
                iWriter.write(bands[passedValues.get(random)] + "\n");
                passedValues.remove(random);
                outerCounter++;
            } while(outerCounter != bands.length);
            iWriter.close();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void sortDataInFile(String dir, String fileName){
        try{
            InputFileReader = new FileReader(dir + "/" + fileName);
            iReader = new BufferedReader(InputFileReader);
            String []array = new String[bands.length];
            String str;
            int counter = 0;
            while((str = iReader.readLine()) != null){
                array[counter] = str;
                counter++;
            }
            BubbleSort(array);
            sendDataToOutputFile(array);
            iReader.close();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private void BubbleSort(String[] arr){
        String temporary;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1; j++){
                if(arr[j].charAt(0) > arr[j+1].charAt(0)){
                    temporary = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temporary;
                }
            }
        }
    }

    private void sendDataToOutputFile(String []arr){
        try{
            OutputFileWriter = new FileWriter(outputDirectory + "/" + outputFileName);
            oWriter = new BufferedWriter(OutputFileWriter);
            for(int counter = 0; counter < arr.length; counter++){
                oWriter.write(arr[counter] + "\n");
            }
            oWriter.close();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }
}

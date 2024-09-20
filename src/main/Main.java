import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    private JFrame frame;

    private JTextArea outputTextArea;
    private JTextField inputTextField;
    private JButton submitButton;
    private JButton playButton;
    private JButton leftButton;
    private JButton forwardButton;
    private JButton rightButton;
    private JPanel inputPanel;
    private JPanel movePanel;
    private ActionListener submitListener;
    private String[] settings = new String[11];
    private String healAnswer;
    private int moveCheck;

    private Player player;


    public Main() {

        frame = new JFrame("Text Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        frame.add(outputScrollPane, BorderLayout.CENTER);

        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        movePanel = new JPanel();
        movePanel.setLayout(new BorderLayout());

        inputTextField = new JTextField();
        inputTextField.setEnabled(false);
        inputPanel.add(inputTextField, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        inputPanel.add(submitButton, BorderLayout.EAST);

        leftButton = new JButton("Left");
        forwardButton = new JButton("Forward");
        rightButton = new JButton("Right");
        movePanel.add(leftButton,BorderLayout.WEST);
        movePanel.add(forwardButton, BorderLayout.CENTER);
        movePanel.add(rightButton,BorderLayout.EAST);

        playButton = new JButton("Play");
        frame.add(playButton, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.SOUTH);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setVisible(false);
                submitButton.setEnabled(true);
                inputTextField.setEnabled(true);
                outputTextArea.append("Choose a file: Normal, Desert, Mythical \n");
            }
        });

        submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = inputTextField.getText().toLowerCase();
                fileInput(fileName);
                inputTextField.setText("");
            }
        };
        submitButton.addActionListener(submitListener);

        frame.setVisible(true);
    }

    public void fileInput(String fileName){

        try(BufferedReader inputStream = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("/resources/" + fileName + ".txt")))){
            final int ANIMAL_COUNT = 11;
            for(int i = 0; i<ANIMAL_COUNT; i++)
            {
                    settings[i] = inputStream.readLine();
            }
        }
        catch(IOException e){

            System.err.println("An error occurred while reading input: " + e.getMessage());
        }
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCheck = randomizer(2);
                if(moveCheck == 0){
                    play();
                }
                else if(moveCheck == 1){
                    play();
                }
                else if(moveCheck == 2){
                    play();
                }
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCheck = randomizer(2);
                if(moveCheck == 0){
                    play();
                }
                else if(moveCheck == 1){
                    play();
                }
                else{
                    play();
                }
            }
        });

        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCheck = randomizer(2);
                if(moveCheck == 0){
                    play();
                }
                else if(moveCheck == 1){
                    play();
                }
                else{
                    play();
                }
            }
        });

        outputTextArea.setText("");
        titleScreen();
        outputTextArea.append("So.. What was your name again?\n");

        submitButton.removeActionListener(submitListener);
        submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = inputTextField.getText().toLowerCase();
                inputTextField.setText("");
                createPlayer(name);
                move();

            }
        };
        submitButton.addActionListener(submitListener);
    }
    public void createPlayer(String name){
        player = new Player(name);
        outputTextArea.append("Welcome " + player.getName() + " you are in a " + settings[10] + " and the animals need your help!\n");
    }

    public void move(){
        if(AnimalFive.healed == 1){

            endScreen("You win!\n");
            submitButton.removeActionListener(submitListener);
            submitListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };
            submitButton.addActionListener(submitListener);
            submitButton.setText("Quit");
        }
        else if(player.getHealth() <=0){
            endScreen("You lose!\n");
            submitButton.removeActionListener(submitListener);
            submitListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };
            submitButton.addActionListener(submitListener);
            submitButton.setText("Quit");
        }
        else{
            frame.remove(inputPanel);
            frame.add(movePanel,BorderLayout.SOUTH);

            frame.revalidate();
            frame.repaint();

            outputTextArea.append("Which way would you like to go? \n");

        }
    }

    public void play(){
        frame.remove(movePanel);
        frame.add(inputPanel,BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();

        Animal animal = spawnAnimal();
        outputTextArea.append("You have encountered a " + animal.getName() + " it is " + animal.getInjuryDescription() + "\n");
        outputTextArea.append("Do you want to heal this poor creature?\n");
        submitButton.removeActionListener(submitListener);
        submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                healAnswer = inputTextField.getText().toLowerCase();
                inputTextField.setText("");
                injuryCheck(animal);
            }
        };
        submitButton.addActionListener(submitListener);
    }

    public static int randomizer (int bound) {
        Random randomise = new Random();

        return randomise.nextInt(bound);
    }

    public Animal spawnAnimal(){
        Animal animal;
        int random_number1 = randomizer(4);
        int random_number2 = randomizer(4);

        if (random_number1 == random_number2)
        {
            animal = new AnimalFive(settings);
            AnimalFive.seen++;
        }
        else if(random_number1 == 1)
        {
            animal = new AnimalOne(settings);
            AnimalOne.seen++;
        }
        else if(random_number1 == 2)
        {
            animal = new AnimalTwo(settings);
            AnimalTwo.seen++;
        }
        else if(random_number1 == 3)
        {
            animal = new AnimalThree(settings);
            AnimalThree.seen++;
        }
        else
        {
            animal = new AnimalFour(settings);
            AnimalFour.seen++;
        }
        int injLevel = randomizer(3);
        animal.setInjuryLevel(injLevel);
        animal.setHealth(injLevel);
        animal.setInjuryDescription(injLevel);

        return animal;
    }

    public  void evalAnimal (Player player, Animal animal) {
        int rollCheck = randomizer(15);


        if (healAnswer.equals("yes") && rollCheck > animal.getHealth() - player.getHealSkill())
        {
            incHealed(animal);

            outputTextArea.append("The " + animal.getName() + " let out a happy cry and ran off!\n");
            outputTextArea.append("You have " + player.getHealth() + "hp remaining.\n");

            if (animal.getInjuryLevel() == 1)
            {
                player.incHealSkill();
                outputTextArea.append("Level Up! Your healing ability is now " + player.getHealSkill()+ "\n");
            }

            bonus(player);

        }
        else if (healAnswer.equals("yes") && rollCheck <= animal.health)
        {
            player.decHealth();

            outputTextArea.append("He didn't make it :(\n");
            outputTextArea.append("You have " + player.getHealth() + " hp remaining.\n");
        }
        else
        {
            outputTextArea.append("You left it to fend for itself. You have " + player.getHealth() + " hp remaining.\n");
        }

        move();
    }

    public static void incHealed(Animal animal){
        if(animal instanceof AnimalOne){
            AnimalOne.healed++;
        }
        else if(animal instanceof AnimalTwo){
            AnimalTwo.healed++;
        }
        else if(animal instanceof AnimalThree){
            AnimalThree.healed++;
        }
        else if(animal instanceof AnimalFour){
            AnimalFour.healed++;
        }
        else {
            AnimalFive.healed++;
        }
    }

    public void bonus(Player player){
        if(AnimalOne.healed == 5 && AnimalOne.bonusAvailable){
            player.incHealSkill();
            outputTextArea.append("Level Up! Your healing ability is now " + player.getHealSkill()+ "\n");
            AnimalOne.bonusAvailable = false;
        }
        else if(AnimalTwo.healed == 5 && AnimalTwo.bonusAvailable){
            player.incHealSkill();
            outputTextArea.append("Level Up! Your healing ability is now " + player.getHealSkill()+ "\n");
            AnimalTwo.bonusAvailable = false;
        }
        else if(AnimalThree.healed == 5 && AnimalThree.bonusAvailable){
            player.incHealSkill();
            outputTextArea.append("Level Up! Your healing ability is now " + player.getHealSkill()+ "\n");
            AnimalThree.bonusAvailable = false;
        }
        else if(AnimalFour.healed == 5 && AnimalFour.bonusAvailable){
            player.incHealSkill();
            outputTextArea.append("Level Up! Your healing ability is now " + player.getHealSkill()+ "\n");
            AnimalFour.bonusAvailable = false;
        }
    }

    public void injuryCheck (Animal animal)
    {

        if(animal.injuryDescription.equals("near death!!!") && !healAnswer.equals("yes"))
        {
            outputTextArea.append("You must try and heal this animal!\n");

            submitButton.removeActionListener(submitListener);
            submitListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    healAnswer = inputTextField.getText().toLowerCase();
                    inputTextField.setText("");
                    injuryCheck(animal);
                }
            };
            submitButton.addActionListener(submitListener);
        }
        else {
            evalAnimal(player, animal);
        }
    }

    public void titleScreen ()
    {
        outputTextArea.append("Physician Forest! \n");
        outputTextArea.append(" \n");
        outputTextArea.append("The " + settings[10] + " is in danger and only you can save the creatures who live here. \n");
        outputTextArea.append("Type 'left' 'right' 'forward' or 'back' to explore the " + settings[10] + " and heal the animals you find! \n");
        outputTextArea.append(" \n");

    }

    public void animalsSeen (int times, String animal )
    {

        outputTextArea.append(animal + " " + times+"\n");
    }

    public void endScreen(String message)
    {
        outputTextArea.append(message);
        outputTextArea.append(" You healed:\n");

        animalsSeen(AnimalOne.healed, settings[0]);
        animalsSeen(AnimalTwo.healed, settings[2]);
        animalsSeen(AnimalThree.healed, settings[4]);
        animalsSeen(AnimalFour.healed, settings[6]);
        animalsSeen(AnimalFive.healed, settings[8]);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Main::new);
    }
}



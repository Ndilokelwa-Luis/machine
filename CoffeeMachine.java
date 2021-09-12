package machine;

import java.util.Scanner;

enum MachineStatus {
    START, BUY, FILL_WATER, FILL_MILK, FILL_COFFEE, FILL_CUP
}

public class CoffeeMachine {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] machineState = new int[] {400, 540, 120, 9, 550};

        String action;


        MachineStatus machineStatus = MachineStatus.START;

        do {
            printQuestion(machineStatus);
            action = scanner.next();
            machineStatus = doAction(machineStatus, machineState, action);
        } while (!"exit".equals(action));
    }

    static void buyCoffee(int[] machine, String option) {
        switch (option) {
            case "1":
                if (machine[0] < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if (machine[2] < 16) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (machine[3] < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    machine[0] -= 250;
                    machine[2] -= 16;
                    machine[3]--;
                    machine[4] += 4;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
            case "2":
                if (machine[0] < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if (machine[1] < 75) {
                    System.out.println("Sorry, not enough milk!");
                } else if (machine[2] < 20) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (machine[3] < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    machine[0] -= 350;
                    machine[1] -= 75;
                    machine[2] -= 20;
                    machine[3]--;
                    machine[4] += 7;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
            case "3":
                if (machine[0] < 200) {
                    System.out.println("Sorry, not enough water!");
                } else if (machine[1] < 100) {
                    System.out.println("Sorry, not enough milk!");
                } else if (machine[2] < 12) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (machine[3] < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    machine[0] -= 200;
                    machine[1] -= 100;
                    machine[2] -= 12;
                    machine[3]--;
                    machine[4] += 6;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
            default:
                break;
        }
    }

    static void takeCoffee(int[] machine) {
        System.out.printf("I gave you $%d%n", machine[4]);
        machine[4] = 0;
    }

    static void printMachineState(int[] machine) {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water%n", machine[0]);
        System.out.printf("%d ml of milk%n", machine[1]);
        System.out.printf("%d g of coffee beans%n", machine[2]);
        System.out.printf("%d disposable cups%n", machine[3]);
        System.out.printf("$%d of money%n", machine[4]);
    }

    static void printQuestion(MachineStatus machineStatus) {
        String startString = "Write action (buy, fill, take, remaining, exit):";
        String buyString = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:";
        String fillWater = "Write how many ml of water you want to add:";
        String fillMilk = "Write how many ml of milk you want to add:";
        String fillCoffee = "Write how many grams of coffee beans you want to add:";
        String fillCup = "Write how many disposable cups of coffee you want to add:";

        switch (machineStatus) {
            case START:
                System.out.println(startString);
                break;
            case BUY:
                System.out.println(buyString);
                break;
            case FILL_WATER:
                System.out.println(fillWater);
                break;
            case FILL_MILK:
                System.out.println(fillMilk);
                break;
            case FILL_COFFEE:
                System.out.println(fillCoffee);
                break;
            case FILL_CUP:
                System.out.println(fillCup);
                break;
            default:
                break;
        }
    }

    static MachineStatus doAction(MachineStatus machineStatus, int[] machineState, String action) {
        switch (machineStatus) {
            case START:
                switch (action) {
                    case "buy":
                        return MachineStatus.BUY;
                    case "fill":
                        return MachineStatus.FILL_WATER;
                    case "take":
                        takeCoffee(machineState);
                        return MachineStatus.START;
                    case "remaining":
                        printMachineState(machineState);
                        return MachineStatus.START;
                    default:
                        return MachineStatus.START;
                }
            case BUY:
                buyCoffee(machineState, action);
                return MachineStatus.START;
            case FILL_WATER:
                machineState[0] += Integer.parseInt(action);
                return MachineStatus.FILL_MILK;
            case FILL_MILK:
                machineState[1] += Integer.parseInt(action);
                return MachineStatus.FILL_COFFEE;
            case FILL_COFFEE:
                machineState[2] += Integer.parseInt(action);
                return MachineStatus.FILL_CUP;
            case FILL_CUP:
                machineState[3] += Integer.parseInt(action);
                return MachineStatus.START;
            default:
                return MachineStatus.START;
        }
    }
}

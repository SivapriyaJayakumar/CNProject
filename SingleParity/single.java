/*Scanner scan=new Scanner(System.in);
    System.out.println("Enter no of bits");
    int n=scan.nextInt();
    System.out.println("Enter Data");
    int data_sender[]=new int[n+1];
    for(int i=0;i<n;i++){
        data_sender[i]=scan.nextInt();
    }
    System.out.println("Enter 1 for even and 2 for odd");
    int askparity=scan.nextInt();
    switch(askparity){
        case 1:
        int parityeven=obj.calcEvenParity(data_sender);
        data_sender[n]=parityeven;
        break;

        case 2:
        int parityodd=obj.calcOddParity(data_sender);
        data_sender[n]=parityodd;
        break;
    }
    System.out.println("Data with a Parity bit P");
    for(int i=0;i<n+1;i++){
        System.out.print(data_sender[i]);
    }
    System.out.println();
    System.out.println("Enter data that has been received");
    int data_receiver[]=new int[n+1];
    for(int i=0;i<n+1;i++){
        data_receiver[i]=scan.nextInt();
    }

    System.out.println("Data Received");
    for(int i=0;i<n+1;i++){
        System.out.print(data_receiver[i]);
    }
    
    System.out.println();
    switch(askparity){
        case 1:
        int parityeven=obj.checkEvenParity(data_receiver);
        if(parityeven==0){
            System.out.println("NO ERROR");
        }
        else if(parityeven==1){
            System.out.println("ERROR");
        }
        break;

        case 2:
        int parityodd=obj.checkOddParity(data_receiver);
        if(parityodd==1){
            System.out.println("NO ERROR");
        }
        else if(parityodd==0){
            System.out.println("ERROR");
        }
        break;
    }*/
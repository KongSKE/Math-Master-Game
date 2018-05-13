# Math Master Game

##### Author by
- Dacharat Pankong(@dacharat)
- Varit Ass (@kongSKE14)

See github page: https://dacharat.github.io/Math-Master-Game/

Download game [here](https://github.com/dacharat/Math-Master-Game/blob/master/MathMaster.jar)!!

Math Master Game is a game that find the master mathematics. There are 3 mini games,
Calculadola, QuestionIs, and MakeIt24. Player can choose game  to play and each mini game will collect a player’s score to show who is the master of each game.

## UML

![mathmastergame](https://user-images.githubusercontent.com/25238368/39967724-407e77d4-56eb-11e8-961d-f7627272408f.png)

## How to play

1. When you start program.

- it will ask you to login. If you don't have you username, you can click register.
![image](https://user-images.githubusercontent.com/25238368/39966880-02d31b96-56dd-11e8-9642-8d75ce513758.png)

2. After login, you will go to home page for choose games to play.

- Calculadola is speed calculator game.
- QuestionIs is ask player a question from show answer.
- MakeIt24 is ask player make 4 numbers that given to be 24.
![image](https://user-images.githubusercontent.com/25238368/39966897-4f413c2e-56dd-11e8-9326-7b96b10ffd76.png)

3. In Calculadola game.

- Wait for other player.
![image](https://user-images.githubusercontent.com/25238368/39966941-dc008bf6-56dd-11e8-901b-779f7cb39172.png)

- If other player choose to player this game, game will start.
![image](https://user-images.githubusercontent.com/25238368/39966945-fdae29d4-56dd-11e8-858d-6a2fead1f454.png)

- Play by input the result on input box if it correct player score will increase. When game is end player who have more score will win the game.

4. In QuestionIs game.

- 10 questions for one game.
![image](https://user-images.githubusercontent.com/25238368/39967005-cd905db6-56de-11e8-898d-8a008e76c9e4.png)

- Play by input number in empty box and press enter. Player will get score when it correct.

5. In MakeIt24 game.
- 5 questions for one game.
![image](https://user-images.githubusercontent.com/25238368/39967069-9dcabbb6-56df-11e8-9ff0-6523288eec60.png)

- Play by press on number or operation until player use all number game will calculate player result if it correct player will get score.

6. In Scoreboard.

- Calculadola board.

![image](https://user-images.githubusercontent.com/25238368/39967121-550d0cfc-56e0-11e8-80c4-433d11b355ca.png)
- QuestionIs board.
![image](https://user-images.githubusercontent.com/25238368/39967134-736489dc-56e0-11e8-8f03-b3e2692f6d62.png)
- MakeIt24 board.
![image](https://user-images.githubusercontent.com/25238368/39967149-b4581be8-56e0-11e8-8741-54a5845a1303.png)

## Interesting technology
 ### matheclipse
 > symja-2015-09-26.jar
- Use to calculate number in string like ``eval()`` in Javascript.

### kryonet
> kryonet-2.21-all.jar

- Make game server.

### mysql server
> mysql-connector-java-8.0.11.jar

- Connect game to database.

## Design Pattern

 Strategy => Calculadola in each calculate style

 Singleton => In Account class

 Oberserver => In all extends Task class

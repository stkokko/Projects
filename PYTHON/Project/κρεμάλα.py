def Score(score):
    if(score >= 90  and score <= 100):
        print("You are the master of hangman!")
    elif(score >= 70 and score <= 80):
        print("Very good!")
    elif(score >= 50 and score <= 60):
        print("Not bad.")
    elif(score >= 30 and score <= 40):
        print("You are almost hang!")
    else:
        print("You must hang!")

def figure():
    print("--|")
    print("  O")
    print(" /|\ ")
    print("  |")
    print("_/ " + "\_")
    print(" ####")
    print(" fire")

def building_figure(wrong_ch):
    if(wrong_ch >= 1 and wrong_ch < 9):
                    print("--|")
                    print("  O")
                    if(wrong_ch == 2):
                        print("  |")
                        print("  |")
                    elif(wrong_ch == 3):
                        print(" /|")
                        print("  |")
                    elif(wrong_ch >= 4):
                        print(" /|\ ")
                        print("  |")
                        if(wrong_ch == 5):
                            print(" /")
                        elif(wrong_ch == 6):
                            print(" / "+"\ ")
                        elif(wrong_ch >= 7):
                            print("_/ "+"\_")
                            if(wrong_ch == 8):
                                print( "####")
    
    
print("Purpose of the game for the player is to find the word by guessing the characters,one at a time.If the player reaches nine errors,game is over.")
name = input("Enter your name please: ")
answer = "yes"
alph_list = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]
words_list = ["profile","football","europe","python","computer","athlete","journal","christmas","athens","downtown"]
index_word = input("Enter a number from 0 to 9 please: ")
giv_word = []
score = 0
while(answer == "yes" and len(giv_word) != len(words_list)):
    while(giv_word.count(words_list[int(index_word)]) > 0):
        print("You have play with this word.Please choice another word.")     
        index_word = input("Enter a number from 0 to 9 please: ")
    giv_word.append(words_list[int(index_word)])
    count = 0
    word = ""
    while(count < (len(words_list[int(index_word)]))):
        word += "-"
        count = count + 1
    print("The word you must guess is: " + word)
    giv_ch = []
    wrong_ch = 0
    temp_list = ["-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"]
    while(word != words_list[int(index_word)] and wrong_ch < 9):
        while(1 > 0):
            character = input(name+ " give a character please: ")
            if (alph_list.count(character) > 0):
                giv_ch.append(character)
                break
            else:
                print("This is not character.Please enter a character.")
        i = 0
        n = 0
        length = len(words_list[int(index_word)])
        if(giv_ch.count(character) == 1):
            while (i < length):
                if (character == (words_list[int(index_word)][i])):
                    del temp_list[i]
                    temp_list.insert(i,character)
                    n = n - 1
                    print("You are good!Keep going " +name+" .")
                i = i + 1
                n = n + 1
            k = 0
            word = ""
            while(k < count):
                word = word + temp_list[k]
                k = k + 1
            print(word)
            if(n == length):
                print("This character is not contained in the word")
                wrong_ch = wrong_ch + 1
                building_figure(wrong_ch)
                            
        else:
            print("You gave this character at the past,try again.")
    if(word == words_list[int(index_word)]):
        score = score + 10
        print("Congratulations " + name + " you won!The word is: " + word + ".")
    else:
        figure()
        print("You lost " + name + ".The word was: " + words_list[int(index_word)] + ".")
    answer = input("Do you want to play again " + name + " ?Enter yes or no: ")
if(answer == "no"):
    Score(score)
    print("Thank you for playing!Your score is " + str(score) + "!")
elif(len(giv_word) == len(words_list)):
    Score(score)
    print("No more words.Thank you for playing!Your score is " + str(score) + "!")



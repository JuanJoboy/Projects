def menu():
    print("\nWelcome to the Fitness Calculator!")
    print("\t>1. Convert kilojoules to calories")
    print("\t>2. Convert calories to kilojoules")
    print("\t>3. Calculate kilojoules per gram")
    print("\t>4. Calculate calories per gram")
    print("\t>5. Calculate macros per gram")
    print("\t>6. Calculate BMR and macro-nutrient intake")
    print("\t>7. How healthy is my meal?")
    print("\t>8. Exit")
    # add thing that checks if a meal is healthy, like hungry jacks

    open = True
    while open:
        response = input()
        match response:
            case "1":
                kj_to_cal()
            case "2":
                cal_to_kj()
            case "3":
                kj_gram()
            case "4":
                cal_gram()
            case "5":
                macro_gram()
            case "6":
                bmr_macro()
            case "7":
                health()
            case "8":
                print("Goodbye!")
                open = False
            case _:
                print("Please enter a valid choice.")

def kj_to_cal():
    print("\nYou have selected the kilojoule to calorie converter")
    while True:
        try:
            kilojoules = float(input("Input the amount of kilojoules: "))
            if kilojoules < 0:
                print("\nError: Please enter a non-negative value for kilojoules.")
            else:
                break
        except ValueError:
            print("\nError: Please enter a valid number.")
    
    calories = kilojoules / 4.184
    print(f"\nThe amount of kilojoules is {calories:.2f}")

    if not do_again():
        menu()
    else:
        kj_to_cal()


def cal_to_kj():
    print("\nYou have selected the calorie to kilojoule converter")
    while True:
        try:
            calories = float(input("Input the amount of calories: "))
            if calories < 0:
                print("\nError: Please enter a non-negative value for calories.")
            else:
                break
        except ValueError:
            print("\nError: Please enter a valid number.")
    
    kilojoules = calories * 4.184
    print(f"\nThe amount of kilojoules is {kilojoules:.2f}")
    
    if not do_again():
        menu()
    else:
        cal_to_kj()

def kj_gram():
    print("\nYou have selected the kilojoules per gram calculator")
    while True:
        try:
            grams = float(input("Input the amount of grams of food you're weighing: "))
            if grams < 0:
                print("\nError: Please enter a non-negative value for grams.")
            else:
                break
        except ValueError:
            print("\nError: Please enter a valid number.")

    while True:
        try:
            if grams == 100:
                kilojoules = float(input("Input the amount of grams (Serving size or per 100g): "))
                kj_g = kilojoules / 100
                print(f"\nThe amount of kilojoules per gram is {kj_g:.2f}")
                gram_meal = float(input("\nHow many grams are in your meal?: "))
                kj_meal = kj_g * gram_meal
                print(f"The amount of kilojoules in your meal is {kj_meal}")
                print(f"The amount of calories in your meal is {(kj_meal / 4.184):.2f}")
            else:
                kilojoules = float(input("Input the amount of kilojoules per serving: "))
                kj_g = kilojoules / grams
                print(f"\nThe amount of kilojoules per gram is {kj_g:.2f}")
                gram_meal = float(input("\nHow many grams are in your meal?: "))
                kj_meal = kj_g * gram_meal
                print(f"The amount of kilojoules in your meal is {kj_meal}")
                print(f"The amount of calories in your meal is {(kj_meal / 4.184):.2f}")
            break
        except ValueError:
            print("\nError: Please enter a valid number.")
    
    if not do_again():
        menu()
    else:
        kj_gram()

def cal_gram():
    print("\nYou have selected the calories per gram calculator")
    while True:
        try:
            grams = float(input("Input the amount of grams (Serving size or per 100g): "))
            if grams < 0:
                print("\nError: Please enter a non-negative value for grams.")
            else:
                break
        except ValueError:
            print("\nError: Please enter a valid number.")

    while True:
        try:
            if grams == 100:
                calories = float(input("Input the amount of calories per 100g: "))
                cal_g = calories / 100
                print(f"The amount of calories per gram is {cal_g:.2f}")
                gram_meal = float(input("\nHow many grams are in your meal?: "))
                cal_meal = cal_g * gram_meal
                print(f"The amount of calories in your meal is {cal_meal:.2f}")
            else:
                calories = float(input("Input the amount of calories per serving: "))
                cal_g = calories / grams
                print(f"\nThe amount of calories per gram is {cal_g:.2f}")
                gram_meal = float(input("\nHow many grams are in your meal?: "))
                cal_meal = cal_g * gram_meal
                print(f"The amount of calories in your meal is {cal_meal:.2f}")
            break
        except ValueError:
            print("\nError: Please enter a valid number.")
    
    if not do_again():
        menu()
    else:
        cal_gram()
        
def macro_gram():
    print("\nYou have selected the macro-nutrient per gram calculator")
    while True:
        try:
            grams = float(input("Input the amount of grams (Serving size or per 100g): "))
            if grams < 0:
                print("\nError: Please enter a non-negative value for grams.")
            else:
                break
        except ValueError:
            print("\nError: Please enter a valid number.")

    while True:
        try:
            if grams == 100:
                protein = float(input("Input the amount of protein per 100g: "))
                fat = float(input("Input the amount of fat per 100g: "))
                sat_fat = float(input("Input the amount of saturated fat per 100g: "))
                carbs = float(input("Input the amount of carbs per 100g: "))
                sugars = float(input("Input the amount of sugars per 100g: "))
                sodium = float(input("Input the amount of sodium per 100g: "))
                pro_g = protein / 100
                fat_g = fat / 100
                sat_g = sat_fat / 100
                car_g = carbs / 100
                sug_g = sugars / 100
                sod_mg = sodium
                print(f"The amount per gram is:\nProtein: {pro_g:.3f}g\nFat: {fat_g:.3f}g\nSaturated Fat: {sat_g:.3f}g\nCarbs: {car_g:.3f}g\nSugars: {sug_g:.3f}g\nSodium: {sod_mg:.3f}mg")
                gram_meal = float(input("\nHow many grams are in your meal?: "))
                pro_meal = pro_g * gram_meal
                fat_meal = fat_g * gram_meal
                sat_meal = sat_g * gram_meal
                car_meal = car_g * gram_meal
                sug_meal = sug_g * gram_meal
                sod_meal = (sod_mg * gram_meal) / 1000
                print(f"The amount of macros in your meal is:\nProtein: {pro_meal:.3f}\nFat: {fat_meal:.3f}\nSaturated Fat: {sat_meal:.3f}\nCarbs: {car_meal:.3f}\nSugars: {sug_meal:.3f}\nSodium: {sod_meal:.3f}")
            else:
                protein = float(input("Input the amount of protein per serving: "))
                fat = float(input("Input the amount of fat per serving: "))
                sat_fat = float(input("Input the amount of saturated fat per serving: "))
                carbs = float(input("Input the amount of carbs per serving: "))
                sugars = float(input("Input the amount of sugars per serving: "))
                sodium = float(input("Input the amount of sodium per serving: "))
                pro_g = protein / grams
                fat_g = fat / grams
                sat_g = sat_fat / grams
                car_g = carbs / grams
                sug_g = sugars / grams
                sod_mg = sodium
                print(f"The amount per gram is:\nProtein: {pro_g:.3f}\nFat: {fat_g:.3f}\nSaturated Fat: {sat_g:.3f}\nCarbs: {car_g:.3f}\nSugars: {sug_g:.3f}\nSodium: {sod_mg:.3f}")
                gram_meal = float(input("\nHow many grams are in your meal?: "))
                pro_meal = pro_g * gram_meal
                fat_meal = fat_g * gram_meal
                sat_meal = sat_g * gram_meal
                car_meal = car_g * gram_meal
                sug_meal = sug_g * gram_meal
                sod_meal = (sod_mg * gram_meal) / 1000
                print(f"The amount of macros in your meal is:\nProtein: {pro_meal:.3f}g\nFat: {fat_meal:.3f}g\nSaturated Fat: {sat_meal:.3f}g\nCarbs: {car_meal:.3f}g\nSugars: {sug_meal:.3f}g\nSodium: {sod_meal:.3f}mg")
            break
        except ValueError:
            print("\nError: Please enter a valid number.")
    
    if not do_again():
        menu()
    else:
        macro_gram()

def bmr_macro():
    print("\nYou have selected the Basal Metabolic Rate (BMR) and macro-nutrient calculator")
    while True:
        try:
            weight = float(input("How much do you weigh (kg)?: "))
            height = float(input("How tall are you (cm)?: "))
            age = float(input("How old are you (years)?: "))
            if weight < 0 or height < 0 or age < 0:
                print("\nError: Please enter a non-negative value for weight, height and age.")
            else:
                break
        except ValueError:
            print("\nError: Please enter a valid number.")
            
    while True:
        gender = input("Are you male or female? (m/f): ").lower()
        if gender in ("m", "f"):
            if gender == "m":
                bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5
                ratio(bmr)
                break
            elif gender == "f":
                bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161
                ratio(bmr)
                break
            else:
                print("\nError: Please enter 'm' or 'f'.")
    
    if not do_again():
        menu()
    else:
        bmr_macro()
        
def health():
    print("\nYou have selected 'How healthy is my meal?'")
    while True:
        try:
            energy = float(input("Input your daily calories: "))
            if energy < 0:
                print("\nError: Please enter a non-negative value for grams.")
            else:
                break
        except ValueError:
            print("\nError: Please enter a valid number.")

    while True:
        try:
            calories = float(input("How many calories are in your meal?: "))
            protein = float(input("How much protein is in your meal?: "))
            fat = float(input("How much fat is in your meal?: "))
            sat_fat = float(input("How much saturated fat is in your meal?: "))
            carbs = float(input("How many carbs are in your meal?: "))
            sugars = float(input("How much sugar is in your meal?: "))
            sodium = float(input("How much sodium is in your meal?: "))
            
            print("Select your desired ratio:")
            print("1. Standard (40% Carbs, 30% Protein, 30% Fat)")
            print("2. Low-Carbs (20% Carbs, 40% Protein, 40% Fat)")
            print("3. High-Carbs (50% Carbs, 30% Protein, 20% Fat)")
            choice = int(input("Enter your choice (1-3): "))
            
            if choice == 1:
                protein_ratio = (30 / 100) / 4
                carbs_ratio = (40 / 100) / 4
                sugars_ratio = (10 / 100) / 4
                fat_ratio = (30 / 100) / 9
                sat_fat_ratio = (10 / 100) / 9
            elif choice == 2:
                protein_ratio = (40 / 100) / 4
                carbs_ratio = (20 / 100) / 4
                sugars_ratio = (10 / 100) / 4
                fat_ratio = (40 / 100) / 9
                sat_fat_ratio = (10 / 100) / 9
            elif choice == 3:
                protein_ratio = (30 / 100) / 4
                carbs_ratio = (50 / 100) / 4
                sugars_ratio = (10 / 100) / 4
                fat_ratio = (20 / 100) / 9
                sat_fat_ratio = (10 / 100) / 9
            else:
                print("Invalid choice. Please select a valid option (1-4).")
                break
        except ValueError:
            print("\nError: Please enter a valid number.")
            
        p = float(energy * protein_ratio)
        c = float(energy * carbs_ratio)
        s = float(energy * sugars_ratio)
        f = float(energy * fat_ratio)
        s_f = float(energy * sat_fat_ratio)

        calories_di = float((calories / energy) * 100)
        protein_di = float((protein / p) * 100)
        carbs_di = float((carbs / c) * 100)
        sugars_di = float((sugars / s) * 100)
        fat_di = float((fat / f) * 100)
        sat_fat_di = float((sat_fat / s_f) * 100)
        sodium_di = float((sodium / 2300) * 100)
        print(f"Energy: {calories_di:.0f}%\nProtein: {protein_di:.0f}%\nFat: {fat_di:.0f}%\nSaturated Fat: {sat_fat_di:.0f}%\nCarbs: {carbs_di:.0f}%\nSugars: {sugars_di:.0f}%\nSodium: {sodium_di:.0f}%")
        break
    
    if not do_again():
        menu()
    else:
        health()
        
def ratio(bmr):
    print(f"\nYour Basal Metabolic Rate (BMR) is {bmr:.0f} calories per day.")
    print(f"If you are sedentary (little or no exercise), your calories per day = {(bmr * 1.2):.0f}")
    print(f"If you are lightly active (light exercise or sports 1-3 days/week), your calories per day = {(bmr * 1.375):.0f}")
    print(f"If you are moderately active (moderate exercise 3-5 days/week), your calories per day = {(bmr * 1.55):.0f}")
    print(f"If you are very active (hard exercise 6-7 days/week), your calories per day = {(bmr * 1.725):.0f}")
    print(f"If you are super active (very hard exercise and a physical job), your calories per day = {(bmr * 1.9):.0f}")

    macro = float(input("\nTo calculate your macros, input the amount of calories per day you need: "))
    print("Select your desired macro ratio:")
    print("1. Standard (40% Carbs, 30% Protein, 30% Fat)")
    print("2. Low-Carbs (20% Carbs, 40% Protein, 40% Fat)")
    print("3. High-Carbs (50% Carbs, 30% Protein, 20% Fat)")
    choice = int(input("Enter your choice (1-3): "))
    
    if choice == 1:
        protein_ratio = (30 / 100) / 4
        carbs_ratio = (30 / 100) / 4
        sugars_ratio = (10 / 100) / 4
        fat_ratio = (20 / 100) / 9
        sat_fat_ratio = (10 / 100) / 9
    elif choice == 2:
        protein_ratio = (40 / 100) / 4
        carbs_ratio = (10 / 100) / 4
        sugars_ratio = (10 / 100) / 4
        fat_ratio = (30 / 100) / 9
        sat_fat_ratio = (10 / 100) / 9
    elif choice == 3:
        protein_ratio = (30 / 100) / 4
        carbs_ratio = (40 / 100) / 4
        sugars_ratio = (10 / 100) / 4
        fat_ratio = (10 / 100) / 9
        sat_fat_ratio = (10 / 100) / 9
    else:
        print("Invalid choice. Please select a valid option (1-4).")
    
    protein = float(macro * protein_ratio)
    carbs = float(macro * carbs_ratio)
    sugars = float(macro * sugars_ratio)
    fat = float(macro * fat_ratio)
    sat_fat = float(macro * sat_fat_ratio)
    print(f"Protein: {protein:.0f}g\nFat: {fat:.0f}g\nSaturated Fat: {sat_fat:.0f}g\nCarbs: {carbs:.0f}g\nSugars: {sugars:.0f}g\nSodium: >2300 milligrams.")

def do_again():
    print("\nWould you like to do it again?")
    print("\t> Y")
    print("\t> N")
    
    while not 'y' or 'n':
        play = input("Enter your choice: ").lower()
        if play == 'y':
            return True
        elif play == 'n':
            return False
        else:
            print("Invalid input. Please enter Y or N.")
            
menu()
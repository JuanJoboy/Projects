import tkinter as tk
from tkinter import messagebox

class FitnessCalculatorApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Fitness Calculator")
        
        self.create_menu()

    def create_menu(self):
        self.clear_frame()

        title_label = tk.Label(self.root, text="Welcome to the Fitness Calculator!", font=('Helvetica', 16))
        title_label.pack(pady=10)

        buttons = [
            ("Convert kilojoules to calories", self.kj_to_cal),
            ("Convert calories to kilojoules", self.cal_to_kj),
            ("Calculate kilojoules per gram", self.kj_gram),
            ("Calculate calories per gram", self.cal_gram),
            ("Calculate macros per gram", self.macro_gram),
            ("Calculate BMR and macro-nutrient intake", self.bmr_macro),
            ("How healthy is my meal?", self.health),
            ("Exit", self.root.quit)
        ]

        for (text, func) in buttons:
            button = tk.Button(self.root, text=text, command=func, width=30, height=2)
            button.pack(pady=5)

    def clear_frame(self):
        for widget in self.root.winfo_children():
            widget.destroy()

    def back_to_menu(self):
        self.create_menu()

    def do_again(self, func):
        if messagebox.askyesno("Repeat?", "Would you like to do it again?"):
            func()
        else:
            self.back_to_menu()

    def kj_to_cal(self):
        self.clear_frame()
        
        label = tk.Label(self.root, text="Input the amount of kilojoules:", font=('Helvetica', 12))
        label.pack(pady=10)
        
        entry = tk.Entry(self.root, font=('Helvetica', 12))
        entry.pack(pady=5)
        
        def convert():
            try:
                kilojoules = float(entry.get())
                if kilojoules < 0:
                    raise ValueError("Please enter a non-negative value for kilojoules.")
                calories = kilojoules / 4.184
                messagebox.showinfo("Result", f"The amount of calories is {calories:.2f}")
                self.do_again(self.kj_to_cal)
            except ValueError as e:
                messagebox.showerror("Error", str(e))
        
        button = tk.Button(self.root, text="Convert", command=convert, width=15, height=2)
        button.pack(pady=10)
        
        back_button = tk.Button(self.root, text="Back", command=self.back_to_menu, width=15, height=2)
        back_button.pack(pady=5)

    def cal_to_kj(self):
        self.clear_frame()
        
        label = tk.Label(self.root, text="Input the amount of calories:", font=('Helvetica', 12))
        label.pack(pady=10)
        
        entry = tk.Entry(self.root, font=('Helvetica', 12))
        entry.pack(pady=5)
        
        def convert():
            try:
                calories = float(entry.get())
                if calories < 0:
                    raise ValueError("Please enter a non-negative value for calories.")
                kilojoules = calories * 4.184
                messagebox.showinfo("Result", f"The amount of kilojoules is {kilojoules:.2f}")
                self.do_again(self.cal_to_kj)
            except ValueError as e:
                messagebox.showerror("Error", str(e))
        
        button = tk.Button(self.root, text="Convert", command=convert, width=15, height=2)
        button.pack(pady=10)
        
        back_button = tk.Button(self.root, text="Back", command=self.back_to_menu, width=15, height=2)
        back_button.pack(pady=5)

    def kj_gram(self):
        self.clear_frame()
        
        label_grams = tk.Label(self.root, text="Input the amount of grams:", font=('Helvetica', 12))
        label_grams.pack(pady=10)
        
        entry_grams = tk.Entry(self.root, font=('Helvetica', 12))
        entry_grams.pack(pady=5)
        
        label_kj = tk.Label(self.root, text="Input the amount of kilojoules per serving or per 100g:", font=('Helvetica', 12))
        label_kj.pack(pady=10)
        
        entry_kj = tk.Entry(self.root, font=('Helvetica', 12))
        entry_kj.pack(pady=5)
        
        def calculate():
            try:
                grams = float(entry_grams.get())
                kilojoules = float(entry_kj.get())
                if grams < 0 or kilojoules < 0:
                    raise ValueError("Please enter non-negative values.")
                
                if grams == 100:
                    kj_g = kilojoules / 100
                else:
                    kj_g = kilojoules / grams
                
                messagebox.showinfo("Result", f"The amount of kilojoules per gram is {kj_g:.2f}")
                
                label_meal = tk.Label(self.root, text="How many grams are in your meal?", font=('Helvetica', 12))
                label_meal.pack(pady=10)
                
                entry_meal = tk.Entry(self.root, font=('Helvetica', 12))
                entry_meal.pack(pady=5)
                
                def calculate_meal():
                    try:
                        gram_meal = float(entry_meal.get())
                        if gram_meal < 0:
                            raise ValueError("Please enter a non-negative value.")
                        kj_meal = kj_g * gram_meal
                        cal_meal = kj_meal / 4.184
                        messagebox.showinfo("Result", f"The amount of kilojoules in your meal is {kj_meal:.2f}\nThe amount of calories in your meal is {cal_meal:.2f}")
                        self.do_again(self.kj_gram)
                    except ValueError as e:
                        messagebox.showerror("Error", str(e))
                
                button_meal = tk.Button(self.root, text="Calculate Meal", command=calculate_meal, width=15, height=2)
                button_meal.pack(pady=10)
                
            except ValueError as e:
                messagebox.showerror("Error", str(e))
        
        button = tk.Button(self.root, text="Calculate", command=calculate, width=15, height=2)
        button.pack(pady=10)
        
        back_button = tk.Button(self.root, text="Back", command=self.back_to_menu, width=15, height=2)
        back_button.pack(pady=5)

    def cal_gram(self):
        self.clear_frame()
        
        label_grams = tk.Label(self.root, text="Input the amount of grams (Serving size or per 100g):", font=('Helvetica', 12))
        label_grams.pack(pady=10)
        
        entry_grams = tk.Entry(self.root, font=('Helvetica', 12))
        entry_grams.pack(pady=5)
        
        label_cal = tk.Label(self.root, text="Input the amount of calories per serving or per 100g:", font=('Helvetica', 12))
        label_cal.pack(pady=10)
        
        entry_cal = tk.Entry(self.root, font=('Helvetica', 12))
        entry_cal.pack(pady=5)
        
        def calculate():
            try:
                grams = float(entry_grams.get())
                calories = float(entry_cal.get())
                if grams < 0 or calories < 0:
                    raise ValueError("Please enter non-negative values.")
                
                if grams == 100:
                    cal_g = calories / 100
                else:
                    cal_g = calories / grams
                
                messagebox.showinfo("Result", f"The amount of calories per gram is {cal_g:.2f}")
                
                label_meal = tk.Label(self.root, text="How many grams are in your meal?", font=('Helvetica', 12))
                label_meal.pack(pady=10)
                
                entry_meal = tk.Entry(self.root, font=('Helvetica', 12))
                entry_meal.pack(pady=5)
                
                def calculate_meal():
                    try:
                        gram_meal = float(entry_meal.get())
                        if gram_meal < 0:
                            raise ValueError("Please enter a non-negative value.")
                        cal_meal = cal_g * gram_meal
                        messagebox.showinfo("Result", f"The amount of calories in your meal is {cal_meal:.2f}")
                        self.do_again(self.cal_gram)
                    except ValueError as e:
                        messagebox.showerror("Error", str(e))
                
                button_meal = tk.Button(self.root, text="Calculate Meal", command=calculate_meal, width=15, height=2)
                button_meal.pack(pady=10)
                
            except ValueError as e:
                messagebox.showerror("Error", str(e))
        
        button = tk.Button(self.root, text="Calculate", command=calculate, width=15, height=2)
        button.pack(pady=10)
        
        back_button = tk.Button(self.root, text="Back", command=self.back_to_menu, width=15, height=2)
        back_button.pack(pady=5)

    def macro_gram(self):
        self.clear_frame()
        
        label_grams = tk.Label(self.root, text="Input the amount of grams (Serving size or per 100g):", font=('Helvetica', 12))
        label_grams.pack(pady=10)
        
        entry_grams = tk.Entry(self.root, font=('Helvetica', 12))
        entry_grams.pack(pady=5)
        
        labels = [
            "Protein",
            "Fat",
            "Saturated Fat",
            "Carbs",
            "Sugars",
            "Sodium (mg)"
        ]
        
        entries = {}
        for label_text in labels:
            label = tk.Label(self.root, text=f"Input the amount of {label_text}:", font=('Helvetica', 12))
            label.pack(pady=5)
            entry = tk.Entry(self.root, font=('Helvetica', 12))
            entry.pack(pady=5)
            entries[label_text] = entry
        
        def calculate():
            try:
                grams = float(entry_grams.get())
                if grams < 0:
                    raise ValueError("Please enter a non-negative value for grams.")
                
                macros = {}
                for label_text, entry in entries.items():
                    value = float(entry.get())
                    if value < 0:
                        raise ValueError(f"Please enter a non-negative value for {label_text}.")
                    macros[label_text] = value
                
                if grams == 100:
                    for key in macros:
                        macros[key] /= 100
                else:
                    for key in macros:
                        macros[key] /= grams
                
                result = "The amount per gram is:\n"
                for key, value in macros.items():
                    result += f"{key}: {value:.3f}\n"
                messagebox.showinfo("Result", result)
                
                label_meal = tk.Label(self.root, text="How many grams are in your meal?", font=('Helvetica', 12))
                label_meal.pack(pady=10)
                
                entry_meal = tk.Entry(self.root, font=('Helvetica', 12))
                entry_meal.pack(pady=5)
                
                def calculate_meal():
                    try:
                        gram_meal = float(entry_meal.get())
                        if gram_meal < 0:
                            raise ValueError("Please enter a non-negative value.")
                        result = "The amount of macros in your meal is:\n"
                        for key, value in macros.items():
                            result += f"{key}: {value * gram_meal:.3f}\n"
                        messagebox.showinfo("Result", result)
                        self.do_again(self.macro_gram)
                    except ValueError as e:
                        messagebox.showerror("Error", str(e))
                
                button_meal = tk.Button(self.root, text="Calculate Meal", command=calculate_meal, width=15, height=2)
                button_meal.pack(pady=10)
                
            except ValueError as e:
                messagebox.showerror("Error", str(e))
        
        button = tk.Button(self.root, text="Calculate", command=calculate, width=15, height=2)
        button.pack(pady=10)
        
        back_button = tk.Button(self.root, text="Back", command=self.back_to_menu, width=15, height=2)
        back_button.pack(pady=5)

    def bmr_macro(self):
        self.clear_frame()
        
        labels = [
            "Weight (kg)",
            "Height (cm)",
            "Age (years)"
        ]
        
        entries = {}
        for label_text in labels:
            label = tk.Label(self.root, text=f"Input your {label_text}:", font=('Helvetica', 12))
            label.pack(pady=5)
            entry = tk.Entry(self.root, font=('Helvetica', 12))
            entry.pack(pady=5)
            entries[label_text] = entry
        
        label_gender = tk.Label(self.root, text="Are you male or female? (m/f):", font=('Helvetica', 12))
        label_gender.pack(pady=5)
        
        entry_gender = tk.Entry(self.root, font=('Helvetica', 12))
        entry_gender.pack(pady=5)
        
        def calculate():
            try:
                weight = float(entries["Weight (kg)"].get())
                height = float(entries["Height (cm)"].get())
                age = float(entries["Age (years)"].get())
                gender = entry_gender.get().lower()
                
                if weight < 0 or height < 0 or age < 0:
                    raise ValueError("Please enter non-negative values.")
                if gender not in ("m", "f"):
                    raise ValueError("Please enter 'm' or 'f' for gender.")
                
                if gender == "m":
                    bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5
                else:
                    bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161
                
                result = f"Your Basal Metabolic Rate (BMR) is {bmr:.0f} calories per day.\n"
                activity_levels = {
                    "Sedentary (little or no exercise)": 1.2,
                    "Lightly active (light exercise or sports 1-3 days/week)": 1.375,
                    "Moderately active (moderate exercise 3-5 days/week)": 1.55,
                    "Very active (hard exercise 6-7 days/week)": 1.725,
                    "Super active (very hard exercise and a physical job)": 1.9
                }
                
                for activity, factor in activity_levels.items():
                    result += f"If you are {activity}, your calories per day = {(bmr * factor):.0f}\n"
                
                messagebox.showinfo("Result", result)
                self.do_again(self.bmr_macro)
            except ValueError as e:
                messagebox.showerror("Error", str(e))
        
        button = tk.Button(self.root, text="Calculate", command=calculate, width=15, height=2)
        button.pack(pady=10)
        
        back_button = tk.Button(self.root, text="Back", command=self.back_to_menu, width=15, height=2)
        back_button.pack(pady=5)

    def health(self):
        self.clear_frame()
        
        labels = [
            "Daily calories",
            "Calories in your meal",
            "Protein in your meal",
            "Fat in your meal",
            "Saturated fat in your meal",
            "Carbs in your meal",
            "Sugars in your meal",
            "Sodium in your meal"
        ]
        
        entries = {}
        for label_text in labels:
            label = tk.Label(self.root, text=f"Input your {label_text}:", font=('Helvetica', 12))
            label.pack(pady=5)
            entry = tk.Entry(self.root, font=('Helvetica', 12))
            entry.pack(pady=5)
            entries[label_text] = entry
        
        def calculate():
            try:
                energy = float(entries["Daily calories"].get())
                if energy < 0:
                    raise ValueError("Please enter a non-negative value for daily calories.")
                
                meal_data = {}
                for key in labels[1:]:
                    value = float(entries[key].get())
                    if value < 0:
                        raise ValueError(f"Please enter a non-negative value for {key}.")
                    meal_data[key] = value
                
                ratio_options = {
                    1: ("Standard (40% Carbs, 30% Protein, 30% Fat)", 0.4, 0.3, 0.3),
                    2: ("Low-Carbs (20% Carbs, 40% Protein, 40% Fat)", 0.2, 0.4, 0.4),
                    3: ("High-Carbs (50% Carbs, 30% Protein, 20% Fat)", 0.5, 0.3, 0.2)
                }
                
                label_ratio = tk.Label(self.root, text="Select your desired ratio:", font=('Helvetica', 12))
                label_ratio.pack(pady=5)
                
                for key, (text, _, _, _) in ratio_options.items():
                    label = tk.Label(self.root, text=f"{key}. {text}", font=('Helvetica', 12))
                    label.pack(pady=5)
                
                entry_choice = tk.Entry(self.root, font=('Helvetica', 12))
                entry_choice.pack(pady=5)
                
                def calculate_ratio():
                    try:
                        choice = int(entry_choice.get())
                        if choice not in ratio_options:
                            raise ValueError("Invalid choice. Please select a valid option (1-3).")
                        
                        _, carb_ratio, protein_ratio, fat_ratio = ratio_options[choice]
                        
                        protein_needed = energy * protein_ratio / 4
                        carbs_needed = energy * carb_ratio / 4
                        fat_needed = energy * fat_ratio / 9
                        
                        protein_intake = meal_data["Protein in your meal"] / protein_needed * 100
                        carb_intake = meal_data["Carbs in your meal"] / carbs_needed * 100
                        fat_intake = meal_data["Fat in your meal"] / fat_needed * 100
                        sat_fat_intake = meal_data["Saturated fat in your meal"] / (energy * 0.1 / 9) * 100
                        sugars_intake = meal_data["Sugars in your meal"] / (energy * 0.1 / 4) * 100
                        sodium_intake = meal_data["Sodium in your meal"] / 2300 * 100
                        
                        result = f"Energy: {meal_data['Calories in your meal'] / energy * 100:.0f}%\n"
                        result += f"Protein: {protein_intake:.0f}%\n"
                        result += f"Carbs: {carb_intake:.0f}%\n"
                        result += f"Fat: {fat_intake:.0f}%\n"
                        result += f"Saturated Fat: {sat_fat_intake:.0f}%\n"
                        result += f"Sugars: {sugars_intake:.0f}%\n"
                        result += f"Sodium: {sodium_intake:.0f}%\n"
                        
                        messagebox.showinfo("Result", result)
                        self.do_again(self.health)
                    except ValueError as e:
                        messagebox.showerror("Error", str(e))
                
                button_ratio = tk.Button(self.root, text="Calculate Ratio", command=calculate_ratio, width=15, height=2)
                button_ratio.pack(pady=10)
                
            except ValueError as e:
                messagebox.showerror("Error", str(e))
        
        button = tk.Button(self.root, text="Calculate", command=calculate, width=15, height=2)
        button.pack(pady=10)
        
        back_button = tk.Button(self.root, text="Back", command=self.back_to_menu, width=15, height=2)
        back_button.pack(pady=5)

if __name__ == "__main__":
    root = tk.Tk()
    app = FitnessCalculatorApp(root)
    root.mainloop()

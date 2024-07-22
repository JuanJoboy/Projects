function showSection(sectionId) // Takes one parameter: sectionId (a string representing the ID of the section to be shown).
{
    document.querySelectorAll('main section').forEach(section => // Inside the function, the querySelectorAll method is used to select all section elements within the main element. The forEach method is then used to iterate over each selected section.
    {
        section.classList.add('hidden'); // For each section, the classList.add('hidden') method is called to add the hidden class, which hides the section.
    });
    document.getElementById(sectionId).classList.remove('hidden'); // After hiding all sections, the getElementById method is used to select the section with the given sectionId. The classList.remove('hidden') method is then called on the selected section to remove the hidden class, revealing the section.
}

function kjToCal()
{
    let kilojoules = parseFloat(prompt('Input the amount of kilojoules:')); // Uses parseFloat to convert the user's input into a floating-point number.
    if (isNaN(kilojoules) || kilojoules < 0) // The function checks if the input is a valid number using isNaN and if it's a non-negative value. If either condition is not met, an error message is displayed using alert.
    {
        alert('Error: Please enter a non-negative value for kilojoules.');
        return;
    }
    let calories = kilojoules / 4.184;
    alert(`The amount of kilojoules is ${calories.toFixed(2)} calories.`); // The calculated calories are then rounded to two decimal places using toFixed(2).
}

function calToKj()
{
    let calories = parseFloat(prompt('Input the amount of calories:'));
    if (isNaN(calories) || calories < 0)
    {
        alert('Error: Please enter a non-negative value for calories.');
        return;
    }
    let kilojoules = calories * 4.184;
    alert(`The amount of calories is ${kilojoules.toFixed(2)} kilojoules.`);
}

function kjGram()
{
    let grams = parseFloat(prompt("Input the amount of grams of food you're weighing:"));
    if (isNaN(grams) || grams < 0)
    {
        alert('Error: Please enter a non-negative value for grams.');
        return;
    }
    let kilojoules = parseFloat(prompt('Input the amount of kilojoules per serving:'));
    if (isNaN(kilojoules) || kilojoules < 0)
    {
        alert('Error: Please enter a non-negative value for kilojoules.');
        return;
    }
    let kj_g = kilojoules / grams;
    alert(`The amount of kilojoules per gram is ${kj_g.toFixed(2)} kilojoules.`);
}

function calGram()
{
    let grams = parseFloat(prompt('Input the amount of grams (Serving size or per 100g):'));
    if (isNaN(grams) || grams < 0)
    {
        alert('Error: Please enter a non-negative value for grams.');
        return;
    }
    let calories = parseFloat(prompt('Input the amount of calories per serving:'));
    if (isNaN(calories) || calories < 0)
    {
        alert('Error: Please enter a non-negative value for calories.');
        return;
    }
    let cal_g = calories / grams;
    alert(`The amount of calories per gram is ${cal_g.toFixed(2)} calories.`);
}

function macroGram()
{
    let grams = parseFloat(prompt('Input the amount of grams (Serving size or per 100g):'));
    if (isNaN(grams) || grams < 0)
    {
        alert('Error: Please enter a non-negative value for grams.');
        return;
    }
    let protein = parseFloat(prompt('Input the amount of protein per serving:'));
    let fat = parseFloat(prompt('Input the amount of fat per serving:'));
    let sat_fat = parseFloat(prompt('Input the amount of saturated fat per serving:'));
    let carbs = parseFloat(prompt('Input the amount of carbs per serving:'));
    let sugars = parseFloat(prompt('Input the amount of sugars per serving:'));
    let sodium = parseFloat(prompt('Input the amount of sodium per serving:'));
    
    if ([protein, fat, sat_fat, carbs, sugars, sodium].some(isNaN))
    {
        alert('Error: Please enter valid numbers for all nutrients.');
        return;
    }

    let pro_g = protein / grams;
    let fat_g = fat / grams;
    let sat_g = sat_fat / grams;
    let car_g = carbs / grams;
    let sug_g = sugars / grams;
    let sod_mg = sodium;

    alert(`The amount per gram is:
    Protein: ${pro_g.toFixed(3)}g
    Fat: ${fat_g.toFixed(3)}g
    Saturated Fat: ${sat_g.toFixed(3)}g
    Carbs: ${car_g.toFixed(3)}g
    Sugars: ${sug_g.toFixed(3)}g
    Sodium: ${sod_mg.toFixed(3)}mg`);
}

function bmrMacro()
{
    let weight = parseFloat(prompt('How much do you weigh (kg)?:'));
    let height = parseFloat(prompt('How tall are you (cm)?:'));
    let age = parseFloat(prompt('How old are you (years)?:'));
    let gender = prompt('Are you male or female? (m/f):').toLowerCase();
    
    if ([weight, height, age].some(isNaN) || weight < 0 || height < 0 || age < 0 || !['m', 'f'].includes(gender))
    {
        alert('Error: Please enter valid non-negative values for weight, height, age, and gender.');
        return;
    }
    
    let bmr;
    if (gender === 'm')
    {
        bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
    }
    
    else
    {
        bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
    }

    alert(`Your Basal Metabolic Rate (BMR) is ${bmr.toFixed(0)} calories per day.`);

    let activityLevels =
    {
        "Sedentary": bmr * 1.2,
        "Lightly active": bmr * 1.375,
        "Moderately active": bmr * 1.55,
        "Very active": bmr * 1.725,
        "Super active": bmr * 1.9
    };

    let activityMessage = "Based on your activity level, your daily calories are:\n";
    for (let level in activityLevels)
    {
        activityMessage += `${level}: ${activityLevels[level].toFixed(0)} calories\n`;
    }
    alert(activityMessage);

    let macro = parseFloat(prompt('To calculate your macros, input the amount of calories per day you need:'));
    if (isNaN(macro) || macro < 0)
    {
        alert('Error: Please enter a valid non-negative value for calories.');
        return;
    }

    let ratios =
    {
        1: { protein: 0.3, carbs: 0.4, fat: 0.3 },
        2: { protein: 0.4, carbs: 0.2, fat: 0.4 },
        3: { protein: 0.3, carbs: 0.5, fat: 0.2 }
    };

    let choice = parseInt(prompt('Select your desired macro ratio:\n1. Standard (40% Carbs, 30% Protein, 30% Fat)\n2. Low-Carbs (20% Carbs, 40% Protein, 40% Fat)\n3. High-Carbs (50% Carbs, 30% Protein, 20% Fat)\nEnter your choice (1-3):'));
    if (![1, 2, 3].includes(choice))
    {
        alert('Error: Please enter a valid choice (1-3).');
        return;
    }

    let selectedRatios = ratios[choice];
    let protein = macro * selectedRatios.protein / 4;
    let carbs = macro * selectedRatios.carbs / 4;
    let fat = macro * selectedRatios.fat / 9;

    alert(`Based on your daily calorie intake, your macros are:\nProtein: ${protein.toFixed(0)}g\nCarbs: ${carbs.toFixed(0)}g\nFat: ${fat.toFixed(0)}g`);
}

function health()
{
    let dailyCalories = parseFloat(prompt('Input your daily calories:'));
    if (isNaN(dailyCalories) || dailyCalories < 0)
    {
        alert('Error: Please enter a valid non-negative value for daily calories.');
        return;
    }

    let mealCalories = parseFloat(prompt('How many calories are in your meal?:'));
    let protein = parseFloat(prompt('How much protein is in your meal?:'));
    let fat = parseFloat(prompt('How much fat is in your meal?:'));
    let satFat = parseFloat(prompt('How much saturated fat is in your meal?:'));
    let carbs = parseFloat(prompt('How many carbs are in your meal?:'));
    let sugars = parseFloat(prompt('How much sugar is in your meal?:'));
    let sodium = parseFloat(prompt('How much sodium is in your meal?:'));

    if ([mealCalories, protein, fat, satFat, carbs, sugars, sodium].some(isNaN) || mealCalories < 0 || protein < 0 || fat < 0 || satFat < 0 || carbs < 0 || sugars < 0 || sodium < 0)
    {
        alert('Error: Please enter valid non-negative values for all meal components.');
        return;
    }

    let ratios =
    {
        1: { protein: 0.3, carbs: 0.4, fat: 0.3, satFat: 0.1 },
        2: { protein: 0.4, carbs: 0.2, fat: 0.4, satFat: 0.1 },
        3: { protein: 0.3, carbs: 0.5, fat: 0.2, satFat: 0.1 }
    };

    let choice = parseInt(prompt('Select your desired ratio:\n1. Standard (40% Carbs, 30% Protein, 30% Fat)\n2. Low-Carbs (20% Carbs, 40% Protein, 40% Fat)\n3. High-Carbs (50% Carbs, 30% Protein, 20% Fat)\nEnter your choice (1-3):'));
    if (![1, 2, 3].includes(choice))
    {
        alert('Error: Please enter a valid choice (1-3).');
        return;
    }

    let selectedRatios = ratios[choice];

    let p = dailyCalories * selectedRatios.protein / 4;
    let c = dailyCalories * selectedRatios.carbs / 4;
    let s = dailyCalories * selectedRatios.satFat / 9;
    let f = dailyCalories * selectedRatios.fat / 9;

    let caloriesPercent = (mealCalories / dailyCalories) * 100;
    let proteinPercent = (protein / p) * 100;
    let fatPercent = (fat / f) * 100;
    let satFatPercent = (satFat / s) * 100;
    let carbsPercent = (carbs / c) * 100;
    let sugarsPercent = (sugars / (dailyCalories * 0.1 / 4)) * 100;
    let sodiumPercent = (sodium / 2300) * 100;

    alert(`Energy: ${caloriesPercent.toFixed(0)}%
    Protein: ${proteinPercent.toFixed(0)}%
    Fat: ${fatPercent.toFixed(0)}%
    Saturated Fat: ${satFatPercent.toFixed(0)}%
    Carbs: ${carbsPercent.toFixed(0)}%
    Sugars: ${sugarsPercent.toFixed(0)}%
    Sodium: ${sodiumPercent.toFixed(0)}%`);
}

// Add event listeners for button clicks
document.querySelector('button[onclick="showSection(\'kj_to_cal\')"]').addEventListener('click', kjToCal);
document.querySelector('button[onclick="showSection(\'cal_to_kj\')"]').addEventListener('click', calToKj);
document.querySelector('button[onclick="showSection(\'kj_gram\')"]').addEventListener('click', kjGram);
document.querySelector('button[onclick="showSection(\'cal_gram\')"]').addEventListener('click', calGram);
document.querySelector('button[onclick="showSection(\'macro_gram\')"]').addEventListener('click', macroGram);
document.querySelector('button[onclick="showSection(\'bmr_macro\')"]').addEventListener('click', bmrMacro);
document.querySelector('button[onclick="showSection(\'health\')"]').addEventListener('click', health);
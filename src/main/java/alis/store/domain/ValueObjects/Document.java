package alis.store.domain.ValueObjects;

import java.util.regex.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Document {
    
    private static Pattern PATTERN_GENERIC = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
    private static Pattern PATTERN_NUMBERS = Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})");
    
    @Size(min = 11, max = 11, message = "Document must has 11 characters")
    @Positive(message = "Only numbers are permitted")
    private String Number;
    
    private final boolean IsValid;
    
    public Document(String Number) {
        this.Number = Number;
        IsValid = isValid(Number);
    }
    
    private boolean isValid(String cpf) {
        if (cpf != null && PATTERN_GENERIC.matcher(cpf).matches()) {
            cpf = cpf.replaceAll("-|\\.", "");
            if (cpf != null && PATTERN_NUMBERS.matcher(cpf).matches()) {
                int[] numbers = new int[11];
                for (int i = 0; i < 11; i++) numbers[i] = Character.getNumericValue(cpf.charAt(i));
                    int i;
                    int sum = 0;
                    int factor = 100;
                    for (i = 0; i < 9; i++) {
                        sum += numbers[i] * factor;
                        factor -= 10;
                    }
                    int leftover = sum % 11;
                    leftover = leftover == 10 ? 0 : leftover;
                    if (leftover == numbers[9]) {
                        sum = 0;
                        factor = 110;
                        for (i = 0; i < 10; i++) {
                        sum += numbers[i] * factor;
                        factor -= 10;
                    }
                    leftover = sum % 11;
                    leftover = leftover == 10 ? 0 : leftover;
                    return leftover == numbers[10];
                }
            }
        }
        return false;
    }
    
    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    @Override
    public String toString() {
        return Number;
    }
    
}

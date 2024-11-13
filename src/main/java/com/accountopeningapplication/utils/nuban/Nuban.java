package com.accountopeningapplication.utils.nuban;


import com.accountopeningapplication.config.AccountOpeningConfigProperties;
import com.accountopeningapplication.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Nuban implements IGenerateNuban {

    private final AccountOpeningConfigProperties accountOpeningConfigProperties;
    private final AccountRepository accountRepository;

    @Override
    public String generateMerchantNuban() {
        var nuban = generateNuban();
        var isAccountNumberExist = this.accountRepository.existsAccountByAccountNumber(nuban);
        if (isAccountNumberExist) {
            return this.generateMerchantNuban();
        }
        return nuban;
    }

    private String generateNuban() {
        var bankCode = this.accountOpeningConfigProperties.getBankCode();
        var startsWith = this.accountOpeningConfigProperties.getFirstTwoBankAccount();
        int length = 9 - startsWith.length();

        String serialNumber = RandomStringUtils.randomNumeric(length);
        serialNumber = startsWith + serialNumber;
        return this.generate(bankCode, serialNumber);
    }

    /**
     * Multiplies bank code and serial number with seed.
     *
     * @param bankCodeSerialNumber - bank code with serial number
     * @returns multiplied seed
     */
    private int multiplyWithSeed(String bankCodeSerialNumber) {
        int sum = 0;
        String chosenSeed = bankCodeSerialNumber.length() == this.accountOpeningConfigProperties.getSeed().length() ?
                this.accountOpeningConfigProperties.getSeed() : this.accountOpeningConfigProperties.getSeed2();

        for (int i = 0; i < bankCodeSerialNumber.length(); i++) {
            sum += Character.getNumericValue(bankCodeSerialNumber.charAt(i)) *
                    Character.getNumericValue(chosenSeed.charAt(i));
        }

        return sum;
    }

    /**
     * Generates check digit.
     *
     * @param bankCodeSerialNumber - bank code with serial number
     * @returns check digit
     */
    private int generateCheckDigit(String bankCodeSerialNumber) {
        // Step 1. Calculate A*3+B*7+C*3+D*3+E*7+F*3+G*3+H*7+I*3+J*3+K*7+L*3
        int sum = multiplyWithSeed(bankCodeSerialNumber);

        // Step 2: Calculate Modulo 10 of your result i.e. the remainder after dividing by 10
        int remainder = sum % 10;

        // Step 3. Subtract your result from 10 to get the Check Digit
        int checkDigit = 10 - remainder;

        // Step 4. If your result is 10, then use 0 as your check digit
        return checkDigit == 10 ? 0 : checkDigit;
    }

    /**
     * Pads bank code.
     *
     * @param bankCode - bank code
     */
    protected String padBankCode(String bankCode) {
        if (bankCode.length() <= 3) {
            return StringUtils.leftPad(bankCode, 3, '0');
        } else {
            return StringUtils.leftPad(bankCode, 6, '9');
        }
    }

    /**
     * Pads serial number.
     *
     * @param serialNumber - serial number
     * @returns serialized string
     */
    protected String padSerialNumber(String serialNumber) {
        // ensures the length of serialize is 9
        return StringUtils.leftPad(serialNumber, 9, '0');
    }

    /**
     * Generates NUBAN.
     *
     * @param bankCode     - bank code
     * @param serialNumber - serial number
     * @returns NUBAN
     */
    private String generate(String bankCode, String serialNumber) {
        // pad bank code and serial number(this ensures that the length of both, sums up to either 12 or 15 to generate a valid check digit)
        String paddedBankCode = this.padBankCode(bankCode);
        String paddedSerialNumber = this.padSerialNumber(serialNumber);

        // concatenate bank code and serial number
        String bankCodeSerialNumber = paddedBankCode + paddedSerialNumber;

        // generate check digit for CBN
        int checkDigit = this.generateCheckDigit(bankCodeSerialNumber);

        // create NUBAN
        return paddedSerialNumber + checkDigit;
    }


}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AES {
    private int [][] s_box = {
            {0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76},
            {0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0},
            {0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15},
            {0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75},
            {0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84},
            {0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF},
            {0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8},
            {0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2},
            {0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73},
            {0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB},
            {0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79},
            {0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08},
            {0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A},
            {0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E},
            {0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF},
            {0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16}};

    private int[][] inv_s_box = {
            {0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB},
            {0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB},
            {0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E},
            {0x08, 0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25},
            {0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92},
            {0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84},
            {0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06},
            {0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B},
            {0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73},
            {0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E},
            {0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B},
            {0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4},
            {0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F},
            {0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF},
            {0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61},
            {0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D}};
    private int[][] RConTable = {
            {0x01, 0x00, 0x00, 0x00},
            {0x02, 0x00, 0x00, 0x00},
            {0x04, 0x00, 0x00, 0x00},
            {0x08, 0x00, 0x00, 0x00},
            {0x10, 0x00, 0x00, 0x00},
            {0x20, 0x00, 0x00, 0x00},
            {0x40, 0x00, 0x00, 0x00},
            {0x80, 0x00, 0x00, 0x00},
            {0x1B, 0x00, 0x00, 0x00},
            {0x36, 0x00, 0x00, 0x00}};
    private int[] RotWord(int[] col_Matrix){
        int [] temp = new int[4];
        temp[0] = col_Matrix[1];
        temp[1] = col_Matrix[2];
        temp[2] = col_Matrix[3];
        temp[3] = col_Matrix[0];
        return temp;
    }
    private int[][] BytesToState(int [] text_int){
        int [][] mt = new int[4][4];
        for (int i = 0; i < text_int.length; i++){
            mt[i % 4][i / 4] = text_int[i];
        }
        return mt;
    }
    private int[][] SubBytes(int[][] StateInput){
        int[][] StateOutput = new int[4][4];
        for(int i =0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                StateOutput[i][j] = s_box[StateInput[i][j] / 16][StateInput[i][j] % 16];
            }
        }
        return StateOutput;
    }
    private int[][] invSubBytes(int[][] StateInput){
        int[][] StateOutput = new int[4][4];
        for(int i =0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                StateOutput[i][j] = inv_s_box[StateInput[i][j] / 16][StateInput[i][j] % 16];
            }
        }
        return StateOutput;
    }
    private int[][] ShiftRows(int[][] StateInput){
        int[][] StateOutput = new int[4][4];
        StateOutput[0] = Arrays.copyOf(StateInput[0], 4);
        for(int i = 1; i < 4; i++){
            StateOutput[i] = Arrays.copyOf(ShiftRow(StateInput[i],i), 4);
        }
        return StateOutput;
    }
    private int[] ShiftRow(int[] input,int times ){
        int[] output = Arrays.copyOf(input, 4);
        for(int i = 0 ; i < times; i++){
            int[] temp = Arrays.copyOf(output, 4);
            output[3] = temp[0];
            output[0] = temp[1];
            output[1] = temp[2];
            output[2] = temp[3];
        }
        return output;
    }
    private int[][] invShiftRows(int[][] StateInput){
        int[][] StateOutput = new int[4][4];
        StateOutput[0] = Arrays.copyOf(StateInput[0], 4);
        for(int i = 1; i < 4; i++){
            StateOutput[i] = Arrays.copyOf(invShiftRow(StateInput[i],i), 4);
        }
        return StateOutput;
    }
    private int[] invShiftRow(int[] input,int times ){
        int[] output = Arrays.copyOf(input, 4);
        for(int i = 0 ; i < times; i++){
            int[] temp = Arrays.copyOf(output, 4);
            output[1] = temp[0];
            output[2] = temp[1];
            output[3] = temp[2];
            output[0] = temp[3];
        }
        return output;
    }
   private int[][] MixingColumns(int[][] StateInput){
        int[][] StateOutput = new int[4][4];
        for(int i = 0; i < 4; i++){
            StateOutput[0][i] = Mul(0x02, StateInput[0][i]) ^ Mul(0x03, StateInput[1][i]) ^ StateInput[2][i] ^ StateInput[3][i];
            StateOutput[1][i] = StateInput[0][i] ^ Mul(0x02, StateInput[1][i]) ^ Mul(0x03,StateInput[2][i]) ^ StateInput[3][i];
            StateOutput[2][i] = StateInput[0][i] ^ StateInput[1][i] ^ Mul(StateInput[2][i], 0x02) ^ Mul(0x03, StateInput[3][i]);
            StateOutput[3][i] = Mul(0x03,StateInput[0][i]) ^ StateInput[1][i] ^ StateInput[2][i] ^ Mul(0x02, StateInput[3][i]);
        }
        return StateOutput;
    }
    private int[][] invMixingColumns(int[][] StateInput){
        int[][] StateOutput = new int[4][4];
        for(int i = 0; i < 4; i++){
            StateOutput[0][i] = Mul(0x0E, StateInput[0][i]) ^ Mul(0x0B, StateInput[1][i]) ^
                    Mul(0x0D, StateInput[2][i]) ^ Mul(0x09,StateInput[3][i]);
            StateOutput[1][i] = Mul(0x09, StateInput[0][i]) ^ Mul(0x0E, StateInput[1][i]) ^
                    Mul(0x0B, StateInput[2][i]) ^ Mul(0x0D,StateInput[3][i]);
            StateOutput[2][i] = Mul(0x0D, StateInput[0][i]) ^ Mul(0x09, StateInput[1][i]) ^
                    Mul(0x0E, StateInput[2][i]) ^ Mul(0x0B,StateInput[3][i]);
            StateOutput[3][i] = Mul(0x0B, StateInput[0][i]) ^ Mul(0x0D, StateInput[1][i]) ^
                    Mul(0x09, StateInput[2][i]) ^ Mul(0x0E,StateInput[3][i]);
        }
        return StateOutput;
    }
    private int Mul(int a, int b){
        //Russian Peasant Multiplication algorithm
        int p = 0;
        while(a != 0 && b != 0){
            if((b&1) != 0) p ^= a;
            if ((a & 0x80) != 0) a = (a << 1) ^ 0x11B;
            else a <<= 1;
            b >>= 1;
        }
        return p;
    }

    private int[] SubWord(int[] input){
       int[] output = new int[4];
       for(int i = 0; i < 4; i++){
           output[i] = s_box[input[i] / 16][input[i] % 16];
       }
       return output;
    }
    private int[] XORWord(int[] input, int[] RCon){
        int[] tmp = new int[4];
        for (int i =0; i < 4; i ++){
            tmp[i] = input[i] ^ RCon[i];
        }
        return tmp;
    }
    private int[][] AddRoundKey(int[][] StateInput, int[][]Key){
        int[][] Output = new int[4][4];
        for (int i =0; i < 4; i++)
            for (int j = 0; j < 4; j++){
                Output[i][j] = StateInput[i][j] ^ Key[i][j];
            }
        return Output;
    }
    private List<int[][]> KeyExpansion(int [] CipherKey){
        int [][] key_matrix = BytesToState(CipherKey);
        List <int[]> w = new ArrayList<>();
        List <int[][]> RoundKey = new ArrayList<>();
        for (int i =0; i < 4; i++){
            //w.add(key_matrix[i]);
            w.add(new int[]{key_matrix[0][i],key_matrix[1][i],key_matrix[2][i], key_matrix[3][i]});
        }
        for(int i = 4; i < 44; i++){
            if(i % 4 != 0) {
                w.add(XORWord(w.get(i-1),w.get(i-4)));
            }
            else{
                int[] t = XORWord(SubWord(RotWord(w.get(i-1))),RConTable[i / 4 - 1]);
                w.add(XORWord(t,w.get(i-4)));
            }
        }
        for(int i = 0; i < 11; i++){
            int[][] tmp = new int[][]{w.get(i * 4), w.get(i * 4 + 1), w.get(i * 4 + 2), w.get(i * 4 + 3)};
            int[][] Tran_tmp = new int[4][4];
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++){
                    Tran_tmp[k][j] = tmp[j][k];
                }
            RoundKey.add(Tran_tmp);
        }
        return  RoundKey;
    }
    private int[][] EncryptBlock(int[][] InputState, int[][] Key){
        int[][] OutputState = AddRoundKey(MixingColumns(ShiftRows(SubBytes(InputState))), Key);
        print(Key);
        System.out.println();
        return OutputState;
    }
    private int[][] DecryptBlock(int[][] InputState, int[][] Key){
        int[][] OutputState =invMixingColumns(AddRoundKey(invSubBytes(invShiftRows(InputState)),Key));
        print(Key);
        System.out.println();
        return OutputState;
    }

    void print(int[][] a){
        for (int []x: a){
            for (int v: x){
                System.out.print(Integer.toHexString(v) + " ");
            }
            System.out.println();
        }
    }
    public void Encryption(int[] PlainText, int[] CipherKey){
        int[][] PlainText_State = BytesToState(PlainText);
        List <int[][]> RoundKey = KeyExpansion(CipherKey);
        int[][] CipherText = AddRoundKey(PlainText_State, RoundKey.get(0));
        for(int i = 1; i < 10; i ++){
            CipherText = EncryptBlock(CipherText, RoundKey.get(i));
            print(CipherText);
            System.out.println("--------------------");
        }
        CipherText = AddRoundKey(ShiftRows(SubBytes(CipherText)), RoundKey.get(10));
        print(RoundKey.get(10));
        System.out.println();
        print(CipherText);
    }
    public void Decryption(int[] CipherText, int[] CipherKey){
        int[][] PlainText_State = BytesToState(CipherText);
        print(PlainText_State);
        System.out.println("11111111111111111111111111111");
        List <int[][]> RoundKey = KeyExpansion(CipherKey);
        int[][] PlainText = AddRoundKey(PlainText_State, RoundKey.get(10));
        for(int i = 9; i > 0; i --){
            PlainText = DecryptBlock(PlainText, RoundKey.get(i));
            print(PlainText);
            System.out.println("--------------------");
        }
        PlainText = AddRoundKey(invSubBytes(invShiftRows(PlainText)), RoundKey.get(0));
        System.out.println();
        print(PlainText);
    }
}
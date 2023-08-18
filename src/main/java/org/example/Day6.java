package org.example;

import java.util.HashMap;

public class Day6 {

    static Integer aoc6(String input) {
        HashMap<Character, Integer> lastPos = new HashMap<>(4);
        int start = -1;
        for (int i = 0; i < input.length(); i++) {
            if (lastPos.containsKey(input.charAt(i))) {
                Integer newStart = lastPos.get(input.charAt(i));
                while (start < newStart) {
                    lastPos.remove(input.charAt(start + 1));
                    start++;
                }
            } else {
                if (i - start >= 4) {
                    return i + 1;
                }
            }
            lastPos.put(input.charAt(i), i);
        }
        return -1;
    }

    static Integer aoc6a(String input) {
        HashMap<Character, Integer> lastPos = new HashMap<>(14);
        int start = -1;
        for (int i = 0; i < input.length(); i++) {
            if (lastPos.containsKey(input.charAt(i))) {
                Integer newStart = lastPos.get(input.charAt(i));
                while (start < newStart) {
                    lastPos.remove(input.charAt(start + 1));
                    start++;
                }
            } else {
                if (i - start >= 14) {
                    return i + 1;
                }
            }
            lastPos.put(input.charAt(i), i);
        }
        return -1;
    }

    static String day6Input = "bvsvcsssfwwdttzpzqqnjjmbbmpbmpmffnttjllzbblvlvqllcwwqpwwhcclqqhmhbhlbbmtmdmgmpggcmcnmcmfcfddvmmcncrcncbbtssdnnhmmfmfrmrbbzllgtllfnnwhwtwzwjjrsrgsrsbbnjjbhjbbjpplslrssdwswrrcsszpplpfplflnnvdvmvjmmgpghphlhphnhffgqgfgzgddstdsdjjgqglqggtgvgrrgtrtllrvrmmnggftgtdtrttgqttcrcqqjqcqwccdzdldrdbbbmrbbnmmwrmmbcchnhmhchddhpphdhzhbbnvbbtctwcwbcwcfclcwwzvwvddbsdsfftgtqggjllwjwppmvvswsjwsscdcrrbggqzqqbhhjwhjwjtjvttpnpdplljplpqpzqzrqrmqmvvsvrvppfgfzfqffvsfvfcvcddjcddzssgfgtffhtfhthntnvvzhzwhzhbbnvvbtbqtqcqnqwnnvpvwwcqcgqqwrwlwbwrbbjcbbqqpddtbddcldccddjssvnvlnnmggmbbchcdcqqdsdqsdqdvdpvvvbzvbvrrjdrjrbjbrbttqzttmffwcfcnnbjnbbjmmgtmgmgdgdpgdglgzzvnzzztjjwbwvvhbhwbbnbhbddwcwvcvjcvjjnnjqjqjzqjjbgbmbjjnqnqddgfgwfgwfftntvnvtvhvlvslsffhvvwnnhshchmmlwmmwvvnhnggzllphllbqqtgqqffhwwnwzztnzttftptnppdpjdjwdwhdddgqglqldqdzzslsggstsrrcsrcclqqrsqrqjqhhjwjggrjgjnjtjwtwjtjrrvnnbddzqzzfnnbvvmfvmvwvggmsgsrggcjjtftpfflzfzccdgcgzcgzcggrbgbngnpnfngfnfqqfzfbfsbfsslqsstvvfmvfvrrvdrddrlrjlrrngrrgfgvgdggnttqqjbjwbwwchhddpmmcvcppjttbzbrzrdzztgtrrchhhbnnqfnfjnnpncnbnvnfnwwsllvrvddhwdwndnjdjbjttmggfvggjhghrhggczgzttbnttjmmdhdrrnnmppwtwdtdbtdbdffpqplqqfbffjssllmwmnmffcnczzrnznzmmwcmwwpbpcbbfdfrrznnzwnnmjnngmngmgpgrppnhnjhhjnhjhfhbffcfhccvzccnrrsggtjtdtjjqqdmqqcggmzzrhzrrbnnlqqtlqtqhhcffrpffgcfgcgncgnggjgzgvzvtztctmtgmmfwwttrntrtqtptzpzjpjqjffpvffvdfvvjhhggfhfrhrwhhwshsnhshbbjzbjzzlvlfvffmrfffngfgpglgjjsbbvzzjsjvsslgsgsfggwjgjlgjgsjgsstvstvstsffdqdpqddrsrmmjbbqbffzbzjjvzvrvcvhhsjsrrmccgqqjnnplnnrprhppslsqszzpvzvggsqscqqbwqqccsdsswqswwqcqzqddbhhmggswggzllccwvwjjpqqrpplrprgrllfwfmwmhhcmmnppfffvtffndnsdnsnbsnsgnsncnzzwrrqrwwzhzbzbnbqbjjrhhjjjbcjjffmwffjnfjfbbjtbbjhjvhvshvhwvwhvwvwfwwwfvvlddqnnlttqssnpsprpwwttlwtwrwfflggvjggwswgwgtgccdjdtdldrdcdsspjpvjpvvfttjrjdrrjppddbdvdjdwdvdssrhsszsjzzfccmwcwzzlnljlppjdjsdjdhdttrdtdtvddslsmmjnmmhzmhhmwmvmcvclvlnndtdrrfdrfrvfvsfvsszfsfdsfdsfsnsdsnsbsddsdwwzbbfqqhrhmrmzmhzhbbpptptffvtvhhwqqtgtdthhrqrrvttprtprrbcblsmbwqqpffmwnqmrdwvjszzhhhffpqsphcgqhlmsgnmmjsdhbfjscztgfvhnlbcmccrfcbnrwcpvgzztvpjdplmncrbvpzwtdnpfwcpvcbzvbtpqmgztsblnchzlphtzgfqcwnrbpcdhbcgzsbgdmbhhrsjqcngfddwvwvldbftgccbjzrrqjcnhbdfgmpdhdgfqzmnpmjgtgwvqlbwgjzjppgmcjzrfzgnbjjbvmshllzjppscwjzjqnbvrppzlshfvtvnpwljrghhwdhfgtcrstnqtqdzfhvgbrrzfdwgtjpjccwrphsdrsjlrlbnrtdbnhpdtshmqlpjqjdlwvrrflwqhcmnsrmcdnfcnwwqmdjlqmqvpgggrjrvfwtwnmhrgzztvmnqczvgmvpcldgfwmtmnsvdshrjmtsgfstjllmqqmpttfhjnsjflcmnpqtqnvtpbgjjhlwqzmrgrbvcsjvprbtqdrnvrgfjdrlhdjsvhhzvllzgfbdwbtqbqzpllpcmcdrfmrsfnwvvmhcqjblnczltcmmmbqwpztqmdpprwphfwgwnsvnzsldnvbsbcdprnztpbmlfzcwpdvcwbsdflrcvsqlswctvlpvshqpwvtcjbtfnmgwtgsvtqhqdzzfhwznggpsmsmzlzrzqcdbqcnhpwhjbhvnqrcwnvpspwqmsqhdbchgrfcmznchwvftgvbldnvtpgmthbmpvccqwbjnjpzhrstscncsrdzsptzvlzjwnpzshmzbhrhgmnwmjmzvvvbgdjshgnqcmdmjrnhqzwtzpjvdczqzccvpvpqnftvpcwgzrzmmnhjnphfntbgjnscwqjcgfcrhjdsjbqfgzhsftghwwwvjwmhfhbwrwmnfrhcggcrjtjsqndrgsbvddqtwhctltzswmtsgwjgsbvvsvzfhbptjqbvbblzhcmnpcjpgzfhstsnlfvmqbdjhszhwhbqztvfdhnbnmppwvzjpnrhtbgthrdprjpqwpthqwzdnhpngflcmhnrmsbdlfwhjbfgrhfzspmfvvtqrwldzvblbtlwqfgfflvmvthbvdzzbtmmpzchgmldmgnhrfhhjwstbqqrzhmqsbqhjzdmjmllmjgqhhjghgsvrbmrbfjdzghmwpszfmslfwcjrftbqjgbbzddtvjmsgwmhhdzwtwvrgqctvwbwjqvssdvfprjsdgsgwbfmzwzmbnlzbfdqvwbrzpgvngqhfqzfqqsvmtgvsqwrplhfnsqhtvtbbqmpbjlcsfjgngwrztlrjbmcjddjsnbgcpnhdtngzhzvhzpjfcblhglhzfsjdgjdqvvppfrdftfbfldwqjznvrdqdnfscgvhztjwlqgqbjmlsbcrldjhnrsfgthlhplvwgmdnplrtpdvrqbpvnbpzfzwlvrrscvmgzhlcdwglwwrgzqsjqsqfblqljltbvvjwbpmfqjvldspgsbjrdbbwzjntlpqgvdjfvsjznczsppzwfvblffqqhwmhzdzztbvbsjhvjtjrptrdvrbbpnpncltqnhdvwjgzcmdtnbnhlmptjsqlzlpztnrtclgsmgvrvggtzfhpwgjgwtrnshmcpvhprpnrvgprspmwhchlhhsvwpvjdmjrlnjdchngrdgpwzsgqsjnqnqwsctzvsmqgvrpffffcfshbhmqbffwvcjqscrwhbfflwslspwjgdbvcgbtwldzffrdhrlbssjbcsjgbhprgspnjdbmnshzqwdvtnzsrfwbqfrgnlmpvqrrmmmcshzltqbwffprbwqbfthtvszqzcbbljtfjqmwpqssjtdpqlrfmpchrqmdpbsdjcdmglnlgnwvhclqfmbwhtljmscnzzqtwtjqsqrmmnssmqhqjvdlpmstmpwmrcfjbjrchrgvqqzbvtzpcbdvchvghnflllqddlttjwtnfhgqjjgtgsnhpvnwhcbljstshmhpwbqthvngqzrmqpmwqnvrfblpjlqgczfmtwvmlbzslqwdzhfccmrvjwfnwzgpbvgdgttdgtjdlvrmpbdhwjsvvsjqpclrnbtzphvmpfqhhqdmbmqzwphnzzfqjsqvdvbvnpmtnzpdsfzmbttssnssrtpmpvgrrzvcljtqtrqnfmmtsnvtrsjgcmqnttcmp";

}

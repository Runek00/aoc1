package org.example;

import java.util.Arrays;
import java.util.HashSet;

public class Day3 {

    static int aoc3(String input) {
        String[] rucksacks = input.split("""

                """);
        return Arrays.stream(rucksacks)
                .map(Day3::getWrongItem)
                .map(Day3::priority)
                .reduce(Integer::sum)
                .orElse(0);
    }

    static int aoc3a(String input) {
        String[] rucksacks = input.split("""

                """);
        int output = -1;
        HashSet<Character> badgeFinder = new HashSet<>();
        for (int i = 0; i < rucksacks.length; i++) {
            if (i % 3 == 0) {
                output += priority(badgeFinder.stream().findAny().orElse('a'));
                badgeFinder.clear();
                for (char c : rucksacks[i].toCharArray()) {
                    badgeFinder.add(c);
                }
            } else {
                HashSet<Character> repeated = new HashSet<>();
                for (char c : rucksacks[i].toCharArray()) {
                    if (badgeFinder.contains(c)) {
                        repeated.add(c);
                    }
                }
                badgeFinder = repeated;
            }


        }
        output += priority(badgeFinder.stream().findAny().orElse('a'));
        return output;

    }

    static char getWrongItem(String rucksack) {
        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < rucksack.length(); i++) {
            char c = rucksack.charAt(i);
            if (i < rucksack.length() / 2) {
                charSet.add(c);
            } else {
                if (charSet.contains(c)) {
                    return c;
                }
            }
        }
        return 'a';
    }

    static int priority(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }

    static String day3Input = """
            QJRBMDMtRDCtJzBtJMfjNjhwvmNDvwjLVVgh
            TPSNNPZGTjgmSmvfjL
            bPlpZZbpsTlTsWprpGFCJtRtzMNdMMBBcWnJQB
            tppvbQBhpQQdrzMMcLwhMc
            gZnWRccRNgFGRGRFRNNgZgJMddddLLLMCPqwLCNPwqPJ
            nRRmFSnWmlgZlTlTllSlSWWWTsfvfDQpBfBcpQvpVQpTfQQf
            lRlsVFgTlMgRNsSNTlFgmbWnMPppPnMqWZMWPPWW
            fDjgBJdCfCHHBnfLWpqnmnpZmf
            GjQHHcdvJHQBHSSNsFQFslwwRg
            NPwDLDHNwjLLHWjbdSbDfJJQTZsZDS
            BcFBcvgFvghnFLrBpvrgcgrJSZJpQdfSTZbCsSdfZZfbCf
            VrngVFRmrVWHLGVMlL
            SNBBBDlfZDLqNGmgFjjmBsQgCFtF
            VPPVbhpbhMhRhncnScRncbrQtCgQQFmjjjsgtRtQHmFQ
            nhWcPJVhpbvMvwvwllvSlGlD
            wNlNNqtqHHHPhqCz
            MMMMcQSWSpQCWFnRRPchLVvPLLzhmhLzhh
            CrgRSWrnrQpppRQrCTnRTRtGtBDBfbNBllbTJlZtfNBN
            QNbbNrnNnCwHmNPQmzqQNPsCCfBFFGtsBBddBDtCJDJd
            gvVgpZWgTWvRvlvLPDDJjGBfdsdpDDJGdd
            ZRMWWRMVgRZghTggPSMZzQwwnqwmnzhNnNwHcQHm
            VmPHzBmpmQHbVHSpNHBVQCtRPPCPvFFMqqntZCZqMR
            dWlDcfcfcjcfDWjlsZfjJhdGvFLGnLsLqsRnvRvRGGRttC
            wfJhZTllcfdZdfjJfjdmQzHVSzHzgHQTpHpmpV
            qNnqmzmCBfvmDvBm
            HcdhtQdttbbhtVcrVVDMfZvdMBTqsWZMBsWZ
            HQGtctRblwqpNwRN
            SBtBLBMZzPDDNFFDQnVVVnnDmf
            dgCjblRdgRvrbwjJGzQQQzwJVJ
            WpWbCWWvlgrcCHdvvCdvWbSLZzhhZhtLBPPStSPhMSpM
            PlPnGGGzCqqlrqTRsbTmFRWgDPmR
            wwpLtjwpzjDwFWRsWTWW
            NZtJjHNNhHfnCBcJMBlCSz
            wSrwggPrhJhCdddw
            tLMNvMTFhDZdhTBh
            LtMvFttGbNcWRsLFLsccRRgfnSrPjPnfljSfPWlnPhrS
            TSZlwZSSccSHZLHVcllSvmDLmJhjDDffJmGjQjgQQJ
            sdBdzsNnBMBstNNMFhNPNbPzgGfDgJrtrfjCjDrCfJmmDQJf
            BnnBznRsFRFBsspzzbZpSTqVTpHVhpTvlqVW
            VtVjjhdFmCCfhRRzzSDbDzpmgzmvgb
            CHJqrswsWvbvJbpD
            CqCPcZHGHTcsCBQsBrTGHMFnLVQjMjLVVhdhnFQVRL
            tvlPSrlNNvtglTtPccldQdhbQbZdcqqZ
            mRmBGHWmDFRsZqHrfbdhqhZZ
            jjMGjWrJpttNjtgg
            HPtCMJNjvJLMDZRdBgLSBSfsWBgG
            VmnrhwwqhbbzrwnDrqpdWBgfdSdfBGgffGWRdh
            qmnTFbVnpqVzpnvlDFJZDClNZPZN
            NNRFQfzbNWhLHTVh
            dGjptnrPqgvqjccvndnnPPhlHrVVTHLWMHlwmrlHMmVTWm
            tDggGnPqDcPPpPpddjGhggtJCCSssfJbQsDfbZsbsbRZFQ
            bqZWhbvvvqfvhqvQCChhZlllGwlwGjNRrNGrwGwNRQ
            PmspSscJVJStzSVzWJlgwlwNlGRLDGrPgNwN
            pdHmWMVStWJWFBBCCbMhCfbC
            wtwbctGLwGWhGwfWwhNrnLrlrQFNmPNNVrrl
            CSdqZRsMStdJMMSZqPnFmVqPQlnjNjqj
            TMtsTBSSRZBCJStMJSZTHtfvpgvzzWwhbpwhggbzHpbW
            HncMbCwCncHlcbMDMnMFGsNsJVFJGchVTTcmcG
            RRfBRNjRLLJTLTThsq
            zpBRjWRrRvBpNtRWrgwbrwQPDPMDwCnn
            TDcPLTVRjntFwDwDnb
            SJJhffHqHZZgHGSFFbdrGTGnGv
            NQHWZgJQHNHgHQhlLLLBjpRTjLjMNNLM
            sMNnNRNrlGlsZBrGsrFQpclWlWLfpWjtzTfDtpzj
            gvhPgwTgdSHtHDtpDPLp
            gwhSwdvTSTbSgRrZNrrNFFNBGb
            rtZnDHJrrDtGtGHvGHDWfdfwCjcBhjBCffwwLv
            lzVlzsTRsmzVNTspVsMMsmwCLcmjmcdbBBChwfBbCW
            sVTMpTpppsVMsPRPVzMNFqMFwZtQrHZDGqgHZrSQQrQQJDGn
            wGQQMMQvCTPPQnHPBS
            FsWdJddszWrrRRJRTmRmpppRHBNPBppNHp
            rWdFWlFJzbzzMTwcvvMbGMgc
            WTnnTpqSnCLmjGgSgjztgg
            rQRHQvbNLwrgtGtrmDglJt
            PwHRNvQPsvHvPTTpLTcCLVnq
            qTsqJDJHjjfMCSDj
            RnGGNFGznzGVnBCWmfSMSLwWRwSj
            NnBbVQFVCClctHQc
            BHzmfDHfJLGcQBGgQLDcstNttlZgdlltldshgZZg
            PwPPSJwPvSNZlvSl
            CJwwjnJFFnWRMcMzcHMHRzGL
            rmZpvcZcqccsqmqzzzcBRLBZbBBRLBlRGVdfZR
            PwjFggwMDgNFwPgTwNFgtFJjfGLhBLsGRGbfBfBLbbTVLbdf
            DWJwgDWMJJDWCCNHmrnscmqqcnpWSQ
            bsRlVgMhtzHvhRvpzcLSZcTWLGzTTrGc
            QJnDjmqjJdmDqqrGWWsZsZTc
            nQPsnwCJdBDJDDJvhHhpMRCVlhlgRV
            NBNwMCtNgqCHClHClq
            JpQmFrQQfHfWjJTfLTjfLRRFRvnvhvnDGDcRcvVGGV
            HTzzpzzdHgbBZZtdMB
            SWcVvBFBVBjShWhGQtZnHFDHRGGQsR
            pMZpmmJPbwbTTQttrDrRrttT
            mZflqdlbMVcNjdWLSj
            tvjdccdbLjhvhlcjRMvRTCQJmBPBCFRG
            qgnqZfHpZDVnCpZzZJQFQBgmmPFJBmRQJQ
            SHDZDDzpNVpfsNsHqpDSjLwCbbWLChwtwjtCWc
            FsWTbcwmGfFFFrpl
            LMhzdfqjLdHQQnSvldGvnS
            VZjVNzfNLtjzDMhVDNtqDqwJwRmmmZJgmgcbWgRRwCbJ
            ZJbPwwfJcGlwCrrZrMMddMMMtt
            pTNvvSSHmmnbpFRp
            SLSjSLDSQNLHNDbJbcfJclBzjGsz
            WSQCWQWstCWCCgNNsDCZMZDBjjlLPnHMMLPrHlcrcLHHTjTh
            bVFJFwfdRFFgjTPgnc
            GmzRRqvRddbdRRdfJRJfsqsSSsZDsDBQZtCSgpgt
            FPjprPpPCCFpFPHWsWvqnnllQsdLQMMlLtslLQMc
            wmzJgzRSRRJghBbwGBBSbtGfLfGlcNnlltdddQtrMd
            zDmRBmwDrpVFDTVDVp
            FPGqjsZGlDJmzsHcTcTMMs
            SQNLSvdbvVbrSbHcftGcrpHGfMmf
            CNNGSCCdSCZqjqljZF
            GvqpqrpqdqdsdGshSMhhRsSMhhlSlJ
            DLCzzjzBwCbQWtQlRRFRRJFptfffgM
            WzpLbLDbBcLPjWQWDBzzmnvNndHNqZqZNZNvcrNT
            scHCGfWHsvWHVfGsggHfgvVcSLwLLPRwwDLPLllRPDzlPr
            tbjqqNNTlPDTTSrD
            QntmNbNnnddqJqqbFJHZWWHWJWvZHGVJsSsp
            WZjpjwwGBGZQsqBLBHLHSRLP
            mJhtdfVtDVJtvVLSmNRSccPPPlNHcH
            JJLCFDhLCfVGGwbGGCwrGC
            nBnsGSCrptmsLWGhWRvVRJVJ
            rllMZZbcWWLJvhTl
            MHwzczHwwHqZcdzMdbqSmwsssmtNCrBmtrnQNB
            LzwrZNrNzBMrJBzJsfqqntMlVlSfhnhb
            HTDPWDHPTgGHWTGcPFRgFpPPtfqmsfqlccmlSmnblbshqnmm
            jWGgpRGPFRHjzdBBsrBJvj
            hjNghjlwqjzGhwhGwLrMMrsMdsMfczPfsr
            ZJQSFZFZpCTQSZHTTFbcWWPbWsWrdLVmrMWMfr
            tttHSCpFQBQQpJZSJLgBNNDhqhBqvBvvRq
            hLLJJJLcLPLfLwcJDchfhpSmqGbmdQGmGSdbqdbmqGGGdG
            zgCCVVvVCNVssdbqmtMWvbnndD
            rCCZZCVTjVZNzFZJBlflBLccDhBFFB
            wwPPHfCMHQsrcwPbMPMcvQFJvqWgFTZgDFJltgZt
            jRBVLhpNqpBmRhhRdNJZJgWTBBtgZWltZJJJ
            mSjndhSzphjLRVqmhphNShrGMGrcbGbnGCHGwrwGfbbG
            PVBRhBdlwRtRhRBwtBlVzDcGpVcZnggGzGMMsg
            fFFWQqbFbLWCWvvFbTjjGnsZMfgsZcZzSZGMpSgD
            QJTCCLFFLjFqFbHTbbltmhBNwwcNmthNhlHr
            qwPJJsJdbPdwJddQCRCgCTMTRGGwMG
            cLFcFBZNWWQLSQRfZjpljTGRCgGR
            cFvrcNBFJDhzdQzv
            zTsVTqDqQNtNwwMVmN
            pHpSzPbRrvbRrGzGMwZwlBJmNtclwJpB
            SjHRPfRbffPHqzCCCdTsTzqj
            jnbMBnPjjjFtBtMjFPRtGfvvfzgWWHMfWHTlGgHH
            dCpdqrVrmdpHfTJTCWGJgG
            qVdrppqSTddqNwZcDPPPhZRBPBLBRLjF
            VbHqLlGQlgjLjjQsNvCZTsNjMtCZvT
            SJtttppwwpwBwdPvsvCvBZrvNrTrvM
            JDnWJpDSSpmSwmpPzSwznhDlqGqqtqqHGHLlhblGbR
            RqRJJVMPdRVVpqMdFwmvnSMwZcfCGfDSZc
            CssQgjssvZvjffmS
            zNlbbWTBLWCbCPPFPbVH
            nvQsHSsGvNvnQghTRMrrjpjM
            ttlLDlzPtGDcRRtpZTFjtgMj
            PBLBwPPDzzLwblzffzLlVHHsCCHqsfvCCSsGSNWC
            jHrTrThrtHgttThgHTtfgTgsmZZmBSZGSGsSGfZBZFFmQs
            qCCPdbcCJddbRcsQSGhFzmZqZGmq
            VVNNdVvclDcPbMWMwnnlwhphjp
            ZdBgJqFWNNNqnZZNGsBCCCRvrCwCjCssCB
            htDPMSPtMPzPTLMzMTMbRRbTbvwRCjfRfsbWWs
            LhMmtMDWmHlpppplJZJgNd
            mhtsjtbChcpLqmpmzL
            DPlPprrfBrpGHHVGNVHRqcNvvLLqLcvJzzTvLc
            VFfVPrrBQFPlDDwDwBpBtSgQjnghMhCdbSnnhtMM
            DPDMpbsHPDPNtdtrgMtdnQ
            WShWlSCJVlzccSBvBvhVZZWlgTNTrNrrQTjQjjjjgDSgSdNt
            cvmCDvCJCcsRbmpFmqms
            sSfFssmLnLwPtrrmttsFbDvWgCvddVgfgWdRDWlChD
            nnGnHBzqHjqBJGChlRClhvghJWDd
            jNzNcczMcGntPMwwSsSr
            GGPCThCCvCTVWBCBGMVMsTgZJsrZtHNNtrsHJrgH
            zjRwcwwfvSjmwznfzQSHDJtgrNrRNrLDsRrHtD
            fjvzmcfSlSznwcnmnSQnhdlhWBpGpdBqhGhqhVPd
            sHGGqpRqfNRVbDDtVwwzWf
            CCLQZllTQLTcSShTQvjhQLnnWrDzVpwtDDwVDnczwMwM
            vggZLZTldlhpCTlZlZCRRPNRmqdmGBHPFqsGqN
            wwFDFLMDjjCNgNwNlwwgvR
            frPbSJMSSPBqrfppSqrBZqMQhHlmNsRZmmslvghsmhsgggtZ
            TPSPfBQrdJSfTTqSbbBfTfdcGWjFWWFDWnGMjLjGVFCj
            LZRZbHtqnVztHTTTjMBQjQHH
            rJcDGpwwgDwCCWFGSFMSffVWfF
            cNNNgvhNglDnhdzsbLbmVs
            RwmrGVPmNLzdmVpmrVtHDjjgDHHRqjFtngFt
            CBlWhQWlTWshsblFGntjHtGbHG
            WsTSGZSTQZZJpPNdzSrzwvpr
            CVsggSgdwSwghVSTCgVZjJlRvlQNJHJGZVvjvj
            qrrnzrrpDFMzbDbbzrMbBcNjRBHHQHGRRllHHPBNBljl
            rnFppcpWcqnWMLDNsggSmWmsWfggdg
            wjQzPjJcplwmDDBL
            vghWhhnfWqzhftWtfnbFBmnGDnLGDbDmmC
            zZNvZrNsWfgVftNZhQcSdPHPTcPHQQTTJV
            WjvPVbWnbbFvjfLlcplQvLQvCwCl
            sJhmrrTRTDDJHhhsmJhmrNDdQwLQQlHllHwwLpCLclBBlcPC
            RJTRDdmPmmzNTDhnWtzMfMWtqjqqWM
            vvpjqtllDMlHDtDBsPSSfBJFlSffNS
            gwTmJrTcJWrNSmsNBBPfmf
            VzzJzgTnddzWrwngnWqbHqbtLqjqvpvqhbMd
            TlpzwGZGGFmZJdPpRtpHPrpcPs
            CMJCMgQjMQvrfMHtMfHv
            DjnNjCBqCCNnWWgDBQQDnCZwFJwmwwTznmFVwFmzTJJm
            CcDPppDCFdDrFcFsMsdlLVjjLsMHvM
            fqSmmtNGqLNffhHHbsMsbjbjNjbv
            SthSGmLnmfwfWGWhSQGSQRnGpDpJPCDJrBPTcPrDwPzFcpFT
            FdqjDtPWzqPdnPPtPFbssllqLJlqNppsJGppLp
            TwfrcvwRgvfTBWRgBssJhspHfffJHlHNGh
            MMZCQrrRBwQCCZMQwcTMwPztnFZSDWVWPttPSZzdzd
            prHlrpJbdccllrrPbFdrgPzZfZhZVhRZVScNRNWtSZjWRW
            LmwCCnvqwGCLMnsWtGRZWVfbfbftRW
            bwnvBnLBvbsBvszHzpgBlPzHHlzg
            grSJNTSgBHgpqhvCGbbZddGCGbbT
            nDLMssQMRLwMtMWRWCZdQfqjfGvZQfCjCc
            PqsDWPMLnwlRllJzghmgmSNhpgrl
            TQGcWQBDnSzzsBSL
            mJJlqJwVJdbSrhlrlhhsLL
            JPtwMtdPbJbVqVNpPtmbpwZcQDFFcCccFjCQjpQWSWZg
            JfbfpZJmzffmpZnZZwsrwDFvwHPP
            RDdQtWTWQQSTGNRhsFsjnvjwrhPjtH
            QccddTVQQldcGGRdGlgmVmBzfVpDmbgggmpL
            HVnhVcHvpVFWDpmP
            QswNZblTTwmqlntDPdqD
            sGZzNwsGNThhMrhBBhzn
            fQllBlVQncgwLlfWwWDvppZZggZqGpZgpGdvGG
            shPTRsFbNFJmvqpGjrpvPDdr
            RNFDtRRRssRTStRmTlnzwSVQlVVWfWzcQc
            WmCpPCWTjQPCWWSjSTmrqRLGDRFGrTFDRFDLDD
            gJnVcnVzdfnZgchvrslMDZGlRRDZLR
            fdHhfncwfbfzJbnJzJfcczhhSmLCCNBjSpjmpjHjBQjpmpNW
            BDvDPGRwRvCmLssGLmsL
            frRjjlldrqtNspLWpqFcCmzm
            ndSnVNtllldrdfSjfNvgVRHBwbbVMRbVPJgH
            PpgjhpVLghPZhSgZVVzzcJWccPNCrcJzrFsJ
            BdBNNMqMdfDnDNTFHHJCqHrJHzrFzF
            wfMNtMndlBTlmTBndRpgghhjZRjvSZVjRw
            ZQnQMWMcjHDHrWNF
            TvtCvvBVgdRdmvBVNzDHlGFjFHjfRfDD
            dvtCCbdJmhvhhhhbhVBPMwqZswnZqZjjMccsZJ
            DDMzRBBSzRDTMQRZsbvssCbhZtCDtP
            dLmwNplnmmwjGvPVCRtVVvVd
            NNmjLmqWJjFRwFSrgcrSHBzcTz
            TwTwTMBWcWBJJBtTWHddCmfgzlCzClsvmfsM
            PPLDnNqPRLQNVnGNVsDQnNmzdhvdddlvdlqgqmdlrfvv
            SQQsjPPLGLbDSnGLLNnWTFZJHbcpFctHZpwJWB
            FzMltgtMzFpZtmzdjPpnvRTQTvRWTDfnnTlvwW
            JcbVcBrqLCVJHJSNCcZVqVqqTRQRWWfNsTfTvDfsWvwTsnwv
            rcZqVJVhmhgPmhmd
            ttvSnlWvWWgcScMDsHHMPMjPmH
            pzLGLfNRpJsvmmfvMDfs
            GhpzRqqpZppNrhvFgwSlWnnBFn
            sbQcDJQJJDbQhwchSctVnVnqTMvMWSqTMPSMlP
            jtjCtNRLNCRgRnlTPPWg
            pzpHdLtFNdJbDhJHsQhs
            pSqnfqDnWPHNPCCHCp
            GdJZQdgZbBvgQLcCZZCCZlPLRH
            PzBgQggbvBthtMdMvbzvVfFfzTWqDmWDqzqWrfff
            nnJdrfgfrdMCMdgrqMnWdgwNTTTzFhPSSHfSHhllzjzNFT
            vBRvmvGZsLZZsHFNFFzTNPzb
            LZVRmcDRvpQLmvvVGDGmpntJJwCWCnCPJwgJDrPDqM
            QddMvdzlVfvdSQmGhmwLbGbmzbns
            JtCCWqqZDsLpGhbGjD
            FNrhqCTWMSRSrQQg
            ZsBZJFsZSmmJsJSmrJrJrvsrdGdCQGQphMGwRMGQRGdbBChM
            FlgfqNNNWnNnHfVnnHdbGwpwGWQhGdRMMdRM
            LFnggHlDqDLvjDmZPcPmvP
            CRHJWfvJvrQfrCsDlGGBszQBjjGB
            LmPHVnMmpLlPssBPlDtd
            MmMSZmVnncMFcmSVHvfSrffCwSvfbHWv
            wsrJrpdJLsMCZDWL
            BbLtGGbNmLQggqgQQtGgMmDCTnWZCZWZTmMmCZnT
            qNBGNNgQcbbtGbbFBLVjfcfwHvrHHJHJcr
            pCZCpdjBljhjBlpVccCpbDDwRWDsLhLbwDsDwsDw
            HNgFSSNvSmdqwsFLFWLGttbw
            gMMndNrzNHnzJZVlMCMCTcpc
            CfsFNszCrrGzrsggsPfPVNVlqTdSjSqMTdSVTdLL
            vRhcHllwJDmnJmDMMdhqSqpVMhdjdp
            cvHRvwQBPZZlrQgz
            TsFhCtQtQsBBLtBLPvgz
            jjWZZjZSMNlNNjljNnlmjjfJLMBGGLvBdzPQpggJJLQzpg
            wjbcmmlnQZmlrTsCFVwshwTr
            nRGFnFjcdlwLSHSpNNnBfWHN
            TgQvPbCMPRhbMPQvtQPvMCRBSHNQHBrQSNfWqpHHrWNWSf
            PCgMbPvTZVDgtPRggtCCbgmmFJJLmcGFLjdmJFcDwJmm
            dgWPssfdvQCLPLhL
            pMtSMtpSmpMpFSMMFZjQCLbLQZZbVbVhNTLblZ
            mpqcpzncfWwhzfRf
            vntvVnRCsvpBpMjCpTpj
            rQdZfhzczNzWcNLTpWgSvjjjpGpMSB
            ZqNDQhfcNchLchQqcDqRHJtHVwnwbtvHsbVs
            qtJGQgTrqtqQdQDgbGjPzZHWWzVjslPZlG
            vBShwRRvvSRSvFvwLSvfcnfBWmHZHVWWHPzlNPWVWjZsWnWV
            cLBFBFhCBLlwpFccFBFftqJDQdgdTDJJCbJgCCdg
            wfmsPvPwNfvmfLNFvzzJbRMnllhlnLhRLC
            gjtqDDTtjgpJcbnMTzCRnCCWhC
            SDqtpGSStVtdqpgBVjBGZmFPJNJmffvfPsHZPZQd
            HQMBBWrQQmPBvmBWnvrTnMSsbFfcfwgfCgscsmGgwgcJGg
            NzzlJLthtlgswGFcwGst
            JqNNRqpzhVRWTSQrrvSQ
            mFpDZjvmtPPGvFjmmGTzTcFRbHczHTbzQgRS
            fNdqhJsNrnnVNhwNVdrdsVczQCcwCMHSTCHgHCRzHgcM
            JlgnNhsqVqNqNpPlvZvDDDGlZZ
            """;
}

package net.sourceforge.pmd.lang.apex.rule.performance;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import java.util.*;

 

public class UseGlobalProcessFacadeForBatchJobsRule extends AbstractApexRule{
    
    ArrayList<String> BatchJobList;
    ArrayList<String> FunctionList;
    public UseGlobalProcessFacadeForBatchJobsRule()
    {
        BatchJobList= new ArrayList<String>(
                Arrays.asList("EndOfDayJob","InitializeSODProcessJob","StartOfDayJob","AccrualEntryJob","ChangeInterestRateJob","LateChargeCreatorJob","PayOffGeneratorJob",
                        "BrokerPayoutJob","InterestPostingJob","InterestPostingAmzJob","PeriodicChargeJob","BillingJob","BillingLocJob","BillingAMZJob","BillingIOAJob",
                        "FloatingRateInterestRevisionJob","DelinquencyProcessingJob","CreditBureauPaymentHistoryJob","AmortizationProcessingJob",
                        "BrokerCommissionDisbJob","SuspendACHForProtectClaimJob","ACHProcessingJob","CreditACHProcessingJob","LoanBlockCodeExpiryProcessingJob",
                        "SODAccountingJob","ProtectClaimBillWaiverJob","ExcessForAmzBasedLoansJob","InvestorFundTxnFilegenJob","FeePaymentFilegenJob","InvestorWithdrawalTxnFilegenJob",
                        "LoanPaymentTransactionCreationJob","LoanPaymentFilegenJob","LoanDisbursalFilegenJob","DisbursalDistributionFilegenJob","InvestorPaymentTxnFilegenJob",
                        "BrokerTxnSweepToACHJob","InvestorFundTxnClearingJob","LoanPaymentTxnClearingJob","LoanPayoffPaymentTxnClearingJob","ExcessforAmzBasedLoansPayoffJob",
                        "LoanRepaymentRefundTxnSweepToACHJob","LoanRescheduleJob","LoanClosureJob","InvestorFundTxnSweepToACHJob","FeePaymentSweepToACHJob","InvestorWithdrawalTxnSweepToACHJob",
                        "LoanDisbursalTxnSweepToACHJob","InvestorPaymentTxnSweepToACHJob","BrokerTxnSweepToACHJob","InvestorFundTxnClearingJob","LoanDiagnosticsJob","InvestorPayoutJob","MonthEndJob",
                        "MaturityProcessingJob","ProcessACHReturnJob","ProcessChargesInterfaceRecsJob","ProcessConsolidatedLoanPayIfaceRecsJob","ProcessDisbursalTxnJob","ProcessDuesInterfaceRecsJob",
                        "ProcessFlexiRateSetupIntRecsJob","ProcessLoanPaymentTxnInterfaceRecsJob","ProcessRepaymentSummaryRecsJob","BankTransactionJob","FloatingRateLoanResetJob","InvestorAMZScheduleCalcJob",
                        "LoanAccountHistoryJob","RegenerateAMZScheduleforIntJob","RegenerateAMZScheduleforPrinAndIntJob","InvestmenOrderInterestAccrualJob")

 

                );
        
        FunctionList= new ArrayList<String>(
                Arrays.asList("startStreamAccrualJobForADay","startSpreadExcessJobForADay","startPeriodicChargeJobForADay","startMaturityJobForADay    ","startLeasePaymentTxnSweepToACHJobForADay","startLeasePaymentTxnGeneratorJobForADay",
                        "startLeasePaymentTxnClearingJobForADay","startLeaseDiagnosticsJobForADay","startLeaseAccrualJobForADay","startLateChargesGenerateJobForADay","startGLReversalEntryJobForADay","startGLEntryJobForADay","startFetchTaxRatesJobForADay","startEvergreenProcessJobForADay","startEvergreenBillingJobForADay",
                        "startEvergreenBillingJobForADay","startDelinquencyJobForADay","startDealerFundingJobForADay","startBillingJobForADay","startApplyTaxRatesJobForADay","startACHJobForADay","startAccrualJobForADay","setUsersDateAndBranch","runEndOfDay","runStartOfDay","runBatchJobs","moveSystemToDate","moveDayBackByNDaysStartedToStarted",
                        "moveDayAheadStartedToStarted","achProcessingJobForADay","moveDayAheadEndedToEnded","startBillingLocJobForADay","moveDayAheadByNDaysStartedToStarted","moveDayAhead","getAllBranchIds","getAllBranches","creditACHProcessingJobForADay"
                        )
                );
    }
    
    
    @Override
    public Object visit( ASTVariableDeclaration node, Object data)
    {
        if(BatchJobList.contains(node.getType()) )
                {
            
                addViolation(data, node);
                 
            
        }
        return data;
    }
    @Override
    public Object visit(ASTMethodCallExpression  node, Object data)
    {
        if(FunctionList.contains(node.getMethodName()) )
                {
            //System.out.println("Methods found "+ node.getMethodName());
            if(!(node.getFullMethodName().contains("GlobalProcessFacade"))){
            //    System.out.println("Full method name"+node.getFullMethodName()); 
                addViolation(data, node);
                 
            }
        }
        
        else if (node.getMethodName().matches("(.*)Billing(.*)Job(.*)Day(.*)" ) || node.getMethodName().matches("(.*)run(.*)Start(.*)Day")|| node.getMethodName().matches("(.*)run(.*)End(.*)Day")|| node.getMethodName().matches("(.*)Batch(.*)Jobs") )
        {
            addViolation(data,node);
        }
        return data;

 

}
}
import sys, argparse
from itertools import islice
import collections

inputfilename = ""
outputfilename = "sentences_CNF.txt"
number_of_sentences = 0

def isImplicationCandidate(logic):
    if logic[0] == 'implies' and len(logic) == 3:
        return True
    else:
        return False
def isIFFCandidate(logic):
    if logic[0] == 'iff' and len(logic) == 3:
        return True
    else:
        return False
def isNPCandidate(logic):
    if logic[0] == 'not' and len(logic) == 2 and len(logic[1]) != 1:
        return True
    else:
        return False
def isDistributionCandidate(logic):
    if logic[0] == 'or':
        for i in range(1, len(logic)):
            if len(logic[i]) > 1:
                if logic[i][0] == 'and':
                    return True
    return False
def cleanUp(logic):
    result = []
    if logic[0] == 'not':
        return logic
    result.append(logic[0])
    outer_op = logic[0]
    for i in range(1, len(logic)):
        if logic[i][0] == outer_op:
            for j in range(1, len(logic[i])):
                result.append(logic[i][j])
        else:
            #append the logic as is
            result.append(logic[i])
            
    return result
def getSimplified(operator, literals, current):
    result = []
    result.append(operator)    if len(literals) == 1:
        result.append(literals[0])
    else:
        result.append(getSimplified(operator, literals[0:len(literals)-1], literals[len(literals)-1]))
    result.append(current)
    return result
def simplify(logic):
    if len(logic) > 3:
        logic = getSimplified(logic[0], logic[1:len(logic)-1], logic[len(logic) - 1])
    for i in range(1, len(logic)):
        if len(logic[i]) > 1:
            logic[i] = simplify(logic[i])
    if len(logic) > 3:
        logic = getSimplified(logic[0], logic[1:len(logic)-1], logic[len(logic) - 1])
    return logic
def isEqual(logic1, logic2):
    if len(logic1) != len(logic2):
        return False
    else:        if len(logic1) == len(logic2) == 1:
            if logic1 == logic2:
                return True
            else:
                return False
        else:
            temp = list(logic2)
            for element in logic1:
                try: 
                    temp.remove(element)
                except ValueError:
                    return False            
            return not temp
def inResult(result, logic):
    for i in range(1, len(result)):
        if isEqual(result[i], logic):
            return True
    return False
def eliminateImplication(logic):
    result = []
    result.append('or')
    result.append(['not', logic[1]])
    result.append(logic[2])
    return result
def eliminateIFF(logic):
    result = []
    result.append('and')
    result.append(eliminateImplication(['implies', logic[1], logic[2]]))
    result.append(eliminateImplication(['implies', logic[2], logic[1]]))
    return result
def propogateNOT(logic):    
    result = []
    if(logic[1][0] == 'or'):
        result.append('and')
    elif(logic[1][0] == 'and'):
        result.append('or')
    elif(logic[1][0] == 'not'):
        return logic[1][1]
    for i in range(1, len(logic[1])):
        if len(logic[1][i]) != 1:
            result.append(propogateNOT(['not', logic[1][i]]))
        else:
            result.append(['not', logic[1][i]])
    return result
def distributeOR(logic):
    result = []
    result.append('and')
    if logic[1][0] == 'and' and logic[2][0] == 'and':
        result.append(parseDistribution(['or', logic[1][1], logic[2][1]]))
        result.append(parseDistribution(['or', logic[1][1], logic[2][2]]))
        result.append(parseDistribution(['or', logic[1][2], logic[2][1]]))
        result.append(parseDistribution(['or', logic[1][2], logic[2][2]])
    else:
        if logic[1][0] == 'and':
            if len(logic[2]) > 2:
                if isDistributionCandidate(logic[2]):
                    logic[2] = parseDistribution(logic[2])
                    result.append(parseDistribution(['or', logic[1][1], logic[2][1]]))
                    result.append(parseDistribution(['or', logic[1][1], logic[2][2]]))
                    result.append(parseDistribution(['or', logic[1][2], logic[2][1]]))
                    result.append(parseDistribution(['or', logic[1][2], logic[2][2]]))
                else:
                    result.append(parseDistribution(['or', logic[1][1], logic[2]]))
                    result.append(parseDistribution(['or', logic[1][2], logic[2]]))
            else:
                result.append(parseDistribution(['or', logic[1][1], logic[2]]))
                result.append(parseDistribution(['or', logic[1][2], logic[2]]))
       else:
            if len(logic[1]) > 2:
                if isDistributionCandidate(logic[1]):
                    logic[1] = parseDistribution(logic[1])
                    result.append(parseDistribution(['or', logic[1][1], logic[2][1]]))
                    result.append(parseDistribution(['or', logic[1][1], logic[2][2]]))
                    result.append(parseDistribution(['or', logic[1][2], logic[2][1]]))
                    result.append(parseDistribution(['or', logic[1][2], logic[2][2]]))
                else:
                    result.append(parseDistribution(['or', logic[1], logic[2][1]]))
                    result.append(parseDistribution(['or', logic[1], logic[2][2]]))
            else:
                result.append(parseDistribution(['or', logic[1], logic[2][1]]))
                result.append(parseDistribution(['or', logic[1], logic[2][2]]))
    return simplify(result)      
def removeDuplicates(logic):
    if len(logic) > 2:
        result = []
        result.append(logic[0])
        result.append(logic[1])
        for i in range(2, len(logic)):
            if not inResult(result, logic[i]):
                result.append(logic[i])
        if len(result) == 2:
            result = result[1]
        return result
    else:
        return logic
def parseCleanUp(logic):
    logic = cleanUp(logic)
    for i in range(1, len(logic)):
        if len(logic[i]) > 1:
            logic[i] = parseCleanUp(logic[i])
    logic = cleanUp(logic)
    return logic
def parseImplications(logic):
    if isIFFCandidate(logic):
        logic = eliminateIFF(logic)
    elif isImplicationCandidate(logic):
        logic = eliminateImplication(logic)
    for i in range(1, len(logic)):
        if len(logic[i]) > 1:
            logic[i] = parseImplications(logic[i])
    if isIFFCandidate(logic):
        logic = eliminateIFF(logic)
    elif isImplicationCandidate(logic):
        logic = eliminateImplication(logic)
    return logic
def parseNOTs(logic):
    if isNPCandidate(logic):
        logic = propogateNOT(logic)
    for i in range(1, len(logic)):
        if len(logic[i]) > 1:
            logic[i] = parseNOTs(logic[i])
    if isNPCandidate(logic):
        logic = propogateNOT(logic)
    return logic
def parseDistribution(logic):
    if isDistributionCandidate(logic):
        logic = distributeOR(logic)
    for i in range(1, len(logic)):
        if len(logic[i]) > 1:
            logic[i] = parseDistribution(logic[i])
    if isDistributionCandidate(logic):
        logic = distributeOR(logic)
    return simplify(logic)
def parseDuplicates(logic):
    logic = removeDuplicates(logic)
    for i in range(1, len(logic)):
        if len(logic[i]) > 1:
            logic[i] = parseDuplicates(logic[i])
    logic = removeDuplicates(logic)
    return logic
def parseLogic(logic):
    if len(logic) == 0:
        return logic
    if len(logic) == 1:
        return logic[0]
    result = parseImplications(logic)
    result = parseNOTs(result)
    result = simplify(result)
    result = parseDistribution(result)
    result = parseCleanUp(result)
    result = parseDuplicates(result)
    return result
parser = argparse.ArgumentParser(description="CNF Converter.")
parser.add_argument('-i', '--input', help='Input file name', required=True)
parser.add_argument('-o', '--output', help='Optional output file name', required=False)
args = parser.parse_args()
inputfilename = args.input
if args.output is not None:
    outputfilename = args.output
with open(inputfilename) as f:
    output_file = open(outputfilename, "w")
    number_of_sentences = int(f.readline().strip())
    for line in islice(f, 0, number_of_sentences):
        cnf = str(parseLogic(eval(line.strip()))).replace("'", "\"")
        if len(cnf) == 1:
            cnf = "\"" + cnf + "\""
        output_file.write(cnf+"\n")
    output_file.close()
             		

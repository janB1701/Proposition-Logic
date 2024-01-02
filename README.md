# Propositional Logic Resolution Algorithm

## Overview
This Java program implements a form of the resolution algorithm for propositional logic, used in automated theorem proving. The resolution algorithm is a rule of inference for deductive reasoning, allowing for the verification of logical consistency or the proving of statements from given premises.

## Key Features
- **Clause Processing**: Parses and processes clauses from a given text file.
- **Resolution Algorithm Implementation**: Implements the resolution algorithm to resolve pairs of clauses.
- **Automated Theorem Proving**: Uses logical resolution to prove target statements or find contradictions in the given set of clauses.

## How It Works
The program reads a file containing clauses in propositional logic, with each line in the file representing a clause. It then attempts to resolve these clauses, looking for complementary pairs of literals that lead to contradictions or the proving of a target statement.

## Usage
To use this program, compile and run it with Java, providing the command "resolution" followed by the path to the text file containing the clauses:
The file should contain clauses formatted as disjunctions of literals, with each clause on a separate line.

## File Format
The input file should contain propositional logic clauses, with each line representing a clause. Literals in a clause should be separated by " v ". For example:
A v B
~A v C
C v ~B


## Sample Output
The program outputs the process of resolving clauses and the conclusion it reaches. If it finds a contradiction or successfully proves the target statement, it outputs the result.

## Contributing
Contributions to enhance the functionality, improve efficiency, or fix bugs are welcome. Please feel free to fork this repository and submit pull requests.

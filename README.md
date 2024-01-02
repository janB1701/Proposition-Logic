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


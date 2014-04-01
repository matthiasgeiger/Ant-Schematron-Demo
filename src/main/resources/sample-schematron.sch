<?xml version="1.0" encoding="UTF-8"?>
<!-- source: Extracted from http://www.schematron.com/resource/Using_Schematron_for_Ant.pdf -->
<!-- Sample schema for use with the Schematron Ant task. 
	@version 16 February 2007 -->
<schema xmlns="http://purl.oclc.org/dsdl/schematron" queryBinding="xslt2">
	<title>Dog Stuff</title>
	<pattern>
		<rule context="Dog">
			<assert test="count(leg) = 4">A dog should have four legs, because then they
				can have four paws.</assert>
			<report test="count(leg) &lt; 3">A dog with less than three legs is unstable
			</report>
		</rule>
		<rule context="Dog/leg">
			<assert test="count(paw) = 1">Each dog's leg should have a single paw, as an
				element or attribute, because this meets the business requirement
				"Dog must be walkable".</assert>
		</rule>
	</pattern>
</schema>